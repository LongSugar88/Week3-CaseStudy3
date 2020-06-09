<%--
  Created by IntelliJ IDEA.
  User: Mr Sugar
  Date: 6/4/2020
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        th{
            text-align: center;
        }
        tr{
            border: 1px solid;
        }
    </style>
</head>
<body>
<h1 style="color: deeppink">Long đẹp trai</h1>

<a href="?action=creat">Add New User</a>
<br>
<a href="?action=find">Find User By ID</a>
<br>
<table border ="1" cellpadding="5" style="border-collapse: collapse">
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <c:forEach items="${myUser}" var= "myuser">
        <tr>
            <td>${myuser.id}</td>
            <td>${myuser.username}</td>
            <td>${myuser.mail}</td>
            <td><a href="?action=edit&id=${myuser.id}">Edit</a></td>
            <td><a href="?action=delete&id=${myuser.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
