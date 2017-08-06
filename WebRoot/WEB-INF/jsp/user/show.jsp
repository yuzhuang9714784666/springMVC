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
    <title>用户信息</title>
</head>
<body>
    <table>
        <tr>
            <td>用户名：${user.username}</td>
            <td>密码：${user.password}</td>
            <td>昵称：${user.nickname}</td>
            <td>邮箱：${user.email}</td>
            <td><a href="${user.username}/update">修改</a></td>
            <td><a href="${user.username}/delete">删除</a></td>
        </tr>
    </table>
</body>
</html>
