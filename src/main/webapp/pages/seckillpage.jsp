<%@ page import="redis.clients.jedis.Jedis" %><%--
  Created by IntelliJ IDEA.
  User: 程祥
  Date: 2022/1/8
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>秒杀</title>
</head>
<body>
    <%
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String kc = jedis.get("kcKey");
    %>
    <h1>限时开启，秒杀IPHONE13</h1>
    <h1>走过路过不要错过~</h1>
    <form action="/secKill/seckill" method="post">
        <input type="submit" value="开始秒杀" />
    </form>
    <label>剩余库存量：</label>
    <%=kc%>
</body>
</html>
