package com.atguigu.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo01 {
    /**
     * 测试连接
     */
    @Test
    public void testConnect() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String ping = jedis.ping();
        System.out.println("连接成功" + ping);
        jedis.close();
    }

    /**
     * 测试清空数据库
     */
    @Test
    public void testInit() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String s = jedis.flushDB();
        System.out.println(s);
    }

    /**
     * 测试设置键
     */
    @Test
    public void testKey() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");
        Set<String> keys = jedis.keys("*");
        for(String key:keys) {
            System.out.println(key);
        }
        System.out.println(jedis.exists("k1"));
        System.out.println(jedis.ttl("k1"));
        System.out.println(jedis.get("k1"));
    }

    /**
     * 测试字符串
     */
    @Test
    public void testString() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.mset("str1","v1","str2","v2","str3","v3");
        System.out.println(jedis.mget("str1","str2","str3"));
    }

    /**
     * 测试列表
     */
    @Test
    public void testList() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.lpush("myList","v1","v2","v3");
        List<String> myList = jedis.lrange("myList", 0, -1);
        for(String str: myList) {
            System.out.println(str);
        }
    }

    /**
     * 测试set
     */
    @Test
    public void testSet() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.sadd("orders","order01","order02","order03");
        Set<String> orders = jedis.smembers("orders");
        for(String str: orders) {
            System.out.println(str);
        }
        jedis.srem("orders","order02");
    }

    /**
     * 测试hash
     */
    @Test
    public void testHash() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.hset("hash1","userName","chengxiang");
        System.out.println(jedis.hget("hash1", "userName"));
        Map<String,String> map = new HashMap<>();
        map.put("telephone","18757557471");
        map.put("address","atguigu");
        map.put("email","abc@163.com");
        jedis.hmset("hash2",map);
        List<String> hmget = jedis.hmget("hash2", "telephone", "email");
        for(String str: hmget) {
            System.out.println(str);
        }
    }

    @Test
    public void testZsort() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.zadd("zset01", 100d, "z3");
        jedis.zadd("zset01", 90d, "l4");
        jedis.zadd("zset01", 80d, "w5");
        jedis.zadd("zset01", 70d, "z6");
        Set<String> zrange = jedis.zrange("zset01", 0, -1);
        for (String e : zrange) {
            System.out.println(e);
        }
    }

}
