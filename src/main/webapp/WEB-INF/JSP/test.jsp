<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored = "false" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <form action = "/student-app/test" method = "POST">
      <input type="text" name = "firstName">
      <input type="text" name = "secondName">
      <input type="submit" value="OK">
    </form>
    <table>
      <thead>
        <tr>
          <th> First Name </th>
          <th> Second Name </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${students}" var="student">
          <tr>
            <td>${student.firstName}</td>
            <td>${student.secondName}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
</body>
</html>