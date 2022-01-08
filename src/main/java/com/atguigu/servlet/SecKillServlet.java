package com.atguigu.servlet;

import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecKillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("uid");
        String productId = req.getParameter("proid");
        //1.判断前端输入的用户名和产品名是否为空
        if("".equals(userId) || "".equals(productId)) {
            System.out.println("请输入用户id或者产品id");
        }
        //2.连接redis数据库
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //3.判断秒杀是否开始即秒杀产品是否在数据库中
        if(jedis.exists("product" + productId)) {
            //3.1 判断秒杀产品是否还有库存(设置库存为3)
            if(jedis.scard("product" + productId) < 4) {
                //3.1.1 判断该用户是否已经秒杀此商品
                if(jedis.sismember("product" + productId,userId)) {
                    System.out.println("您已经抢到了哦，换个商品继续试试！");
                } else {
                    jedis.sadd("product" + productId,userId);
                    System.out.println("秒杀成功！");
                }
            } else {
                System.out.println("已被秒杀完，请下次再来吧！");
            }
        } else {
            System.out.println("商品未开启秒杀，请换个商品试试！");
        }
    }
}
