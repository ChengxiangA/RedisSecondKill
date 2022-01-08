package com.atguigu.redis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneCode {
    public static void main(String[] args) {
        String code = getCode();
        String phone = "18757557471";
        Boolean isSuccess = sendCode(phone, code);
        if(isSuccess == false) {
            System.out.println("24h内只允许发送三次验证码！");
        }
        Boolean isLogin = verifyCode(phone, code);
        if(isLogin == true) {
            System.out.println("登陆成功!");
        } else {
            System.out.println("登陆失败！");
        }
    }

    /**
     * 验证code
     * @param phone
     * @param code
     * @return 验证码是否一致
     */
    public static Boolean verifyCode(String phone,String code) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        if(code.equals(jedis.get("VerifyCode" + phone + ":code"))) {
            return true;
        }
        return false;
    }

    /**
     * 发送验证码
     * @param phone
     * @param code
     * @return 是否成功发送验证码
     */
    public static Boolean sendCode(String phone,String code) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String countKey = "VerifyCode" + phone + ":count";
        String codeKey = "VerifyCode" + phone + ":code";
        String count = jedis.get(countKey);
        if(count == null) {
            jedis.setex(countKey,24*60*60,"1");
            jedis.setex(codeKey,120,code);
        } else if(Integer.parseInt(count) <= 2) {
            jedis.incr(countKey);
            jedis.setex(codeKey,120,code);
        } else {
            jedis.close();
            return false;
        }
        jedis.close();
        return true;
    }

    /**
     * 得到一个随机6位验证码
     * @return 验证码
     */
    public static String getCode() {
        String code = "";
        Random random = new Random();
        for(int i = 1;i <= 6;i ++) {
            int num = random.nextInt(10);
            code += num;
        }
        return code;
    }
}
