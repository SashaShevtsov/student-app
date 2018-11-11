<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored = "false" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
</head>
<body>
    <form action = "/student-app/users" method = "POST">
      <div><span> Username: </span><input type="text" name = "userName"></div>
      <div><span> Password: </span><input type="password" name = "password"></div>
      <div><span> Role: </span><input type="text" name = "role"></div>
      <div>
        <input type="submit" value="OK">
        <input type="reset" value="Clean">
      </div>
    </form>
    <table>
      <thead>
        <tr>
          <th> Username </th>
          <th> Password </th>
          <th> Role </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${users}" var="user">
          <tr >
            <td>${user.userName}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
</body>
</html>