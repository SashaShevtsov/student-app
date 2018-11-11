<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored = "false" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Professors</title>
</head>
<body>
    <form action = "/student-app/professors" method = "POST">
      <div><span> First name: </span><input type="text" name = "firstName"></div>
      <div><span> Second name: </span><input type="text" name = "secondName"></div>
      <div><span> Father name: </span><input type="text" name = "fatherName"></div>
      <div><span> Birth date: </span><input type="text" name = "birthDate"></div>
      <div>
        <input type="submit" value="OK">
        <input type="reset" value="Clean">
      </div>
    </form>
    <table>
      <thead>
        <tr>
          <th> First Name </th>
          <th> Second Name </th>
          <th> Father Name </th>
          <th> Birth date </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${professors}" var="professor">
          <tr >
            <td>${professor.firstName}</td>
            <td>${professor.secondName}</td>
            <td>${professor.fatherName}</td>
            <td>${professor.birthDate}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
</body>
</html>