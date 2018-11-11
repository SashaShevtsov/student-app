<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored = "false" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Marks</title>
</head>
<body>
    <form action = "/student-app/marks" method = "POST">
      <div><span> Mark: </span><input type="text" name = "mark"></div>
      <div><span> Comments: </span><input type="text" name = "comments"></div>
      <div>
        <input type="submit" value="OK">
        <input type="reset" value="Clean">
      </div>
    </form>
    <table>
      <thead>
        <tr>
          <th> Mark </th>
          <th> Date </th>
          <th> Comments </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${marks}" var="mark">
          <tr >
            <td>${mark.mark}</td>
            <td>${mark.date}</td>
            <td>${mark.comments}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
</body>
</html>