<%--
  Created by IntelliJ IDEA.
  User: Mr Sugar
  Date: 6/5/2020
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form method="post" >
    <a href="./myuser">Back</a>
    <fieldset>
        <legend>Find User</legend>
        <table  border ="1" cellpadding="5" style="border-collapse: collapse">
            <tr>
                <td>ID</td>
                <td colspan = "2"><input type="text" name = "findID" placeholder="ID" ></td>
            </tr>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.mail}</td>
            </tr>
            <tr>
                <td><button>Find</button></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
