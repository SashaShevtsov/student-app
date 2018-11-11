<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored = "false" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Groups</title>
</head>
<body>
    <form action = "/student-app/groups" method = "POST">
      <div><span> Group number: </span><input type="text" name = "groupNumber"></div>
      <div>
        <input type="submit" value="OK">
        <input type="reset" value="Clean">
      </div>
    </form>
    <table>
      <thead>
        <tr>
          <th> Group </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${groups}" var="group">
          <tr >
            <td>${group.groupNumber}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
</body>
</html>