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
        jedis.sadd("product1","nobody");
        jedis.sadd("product2","nobody");
        jedis.sadd("product3","nobody");
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
    public void checkKillList() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Set<String> product1 = jedis.smembers("product1");
        Set<String> product2 = jedis.smembers("product2");
        Set<String> product3 = jedis.smembers("product3");
        System.out.println("产品1秒杀名单:");
        for(String user: product1) {
            if(!"nobody".equals(user))
                System.out.println(user);
        }
        System.out.println("产品2秒杀名单:");
        for(String user: product2) {
            if(!"nobody".equals(user))
                System.out.println(user);
        }
        System.out.println("产品3秒杀名单:");
        for(String user: product3) {
            if(!"nobody".equals(user))
                System.out.println(user);
        }
    }
}
