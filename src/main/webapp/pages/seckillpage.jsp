<%--
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
    <form action="/secKill/seckill" method="post">
        <input type="text" name="uid" placeholder="输入用户id"/> <br>
        <input type="text" name="proid" placeholder="输入秒杀产品id" /> <br>
        <input type="submit" value="开始秒杀" />
    </form>
</body>
</html>
