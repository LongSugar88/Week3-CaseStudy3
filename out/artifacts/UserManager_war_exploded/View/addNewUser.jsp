<%--
  Created by IntelliJ IDEA.
  User: Mr Sugar
  Date: 6/4/2020
  Time: 5:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <a href="./myuser">Back</a>
    <fieldset>
        <legend>Add new user</legend>
        <table>
            <tr>
                <td>ID</td>
                <td><input type="text" name = "newID" placeholder="ID"></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name = "newName" placeholder="Name"></td>
            </tr>
            <tr>
                <td>E-mail</td>
                <td><input type="text" name = "mail" placeholder="E-mail"></td>
            </tr>
            <tr>
                <td><button>Save</button></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
