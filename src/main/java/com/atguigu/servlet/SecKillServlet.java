package com.atguigu.servlet;

import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class SecKillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = "" + new Random().nextInt(10000);
        //2.连接redis数据库
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //3.判断是否已经开启秒杀
        if(jedis.exists("kcKey")) {
            //3.1 判断秒杀产品是否还有库存(设置库存为10)
            if(Integer.parseInt(jedis.get("kcKey")) > 0) {
                //3.1.1 判断该用户是否已经秒杀此商品
                if(jedis.sismember("userList",userId)) {
                    System.out.println("您已经抢到了哦，换个商品继续试试！");
                } else {
                    jedis.decr("kcKey");
                    jedis.sadd("userList",userId);
                    System.out.println("秒杀成功！");
                }
            } else {
                System.out.println("已被秒杀完，请下次再来吧！");
            }
        } else {
            System.out.println("商品未开启秒杀，请换个商品试试！");
        }
        resp.sendRedirect("pages/seckillpage.jsp");
    }
}
