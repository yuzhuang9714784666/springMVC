<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2017/8/4
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
    <a href="add">添加用户</a>
    <c:forEach items="${users}" var="us">
        <table>
            <tr>
                <td>用户名：${us.value.username}</td>
                <td>密码：${us.value.password}</td>
                <td>昵称：<a href="${us.value.username}">${us.value.nickname}</a></td>
                <td>邮箱：${us.value.email}</td>
            </tr>
        </table>
    </c:forEach>
</body>
</html>
