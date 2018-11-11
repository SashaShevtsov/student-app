<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored = "false" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Studies</title>
</head>
<body>
    <form action = "/student-app/studies" method = "POST">
      <div><span> Study name: </span><input type="text" name = "name"></div>
      <div><span> Hours: </span><input type="text" name = "hours"></div>
      <div>
        <input type="submit" value="OK">
        <input type="reset" value="Clean">
      </div>
    </form>
    <table>
      <thead>
        <tr>
          <th> Name </th>
          <th> Hours </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${studies}" var="study">
          <tr >
            <td>${study.name}</td>
            <td>${study.hours}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
</body>
</html>