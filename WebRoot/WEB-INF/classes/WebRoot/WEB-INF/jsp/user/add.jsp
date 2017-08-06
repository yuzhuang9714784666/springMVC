<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2017/8/4
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
    <sf:form method="post" modelAttribute="user">
        用户名：<sf:input path="username"/><br>
        密码：<sf:password path="password"/><br>
        昵称：<sf:input path="nickname"/><br>
        邮箱：<sf:input path="email"/><br>
        <input type="submit" value="添加用户"/>
    </sf:form>
</body>
</html>
