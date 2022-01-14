package com.atguigu.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class SecKill {
    /**
     * 执行开始秒杀
     */
    @Test
    public  void initRedisDb() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.sadd("userList","nobody");
        jedis.set("kcKey","10");
    }

    /**
     * 结束秒杀
     */
    @Test
    public  void endSecKill() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
    }

    /**
     * 查看秒杀名单
     */
    @Test
    public void checkUserList() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Set<String> product1 = jedis.smembers("userList");
        for(String user: product1) {
            if(!"nobody".equals(user))
                System.out.println(user);
        }
    }

    /**
     * 查看库存量
     */
    @Test
    public void checkKc() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.get("kcKey"));
    }
}
