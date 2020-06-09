<%--
  Created by IntelliJ IDEA.
  User: Mr Sugar
  Date: 6/5/2020
  Time: 8:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="">
    <a href="./myuser">Back</a>
  <fieldset>
      <legend>Edit user</legend>
      <table>
          <c:if test="${user != null}">
              <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
          </c:if>
          <tr>
              <td>New ID</td>
              <td><input type="text" name = "editID" value="<c:out value='${user.id}' />"></td>
          </tr>
          <tr>
              <td>New Name</td>
              <td><input type="text" name = "editName" value="<c:out value='${user.username}' />"></td>
          </tr>
          <tr>
              <td>New E-mail</td>
              <td><input type="text" name = "editEmail" value="<c:out value='${user.mail}' />"></td>
          </tr>
          <tr>
              <td><button>Save change</button></td>
          </tr>
      </table>
  </fieldset>
</form>
</body>
</html>
