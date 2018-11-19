<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored = "false" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/common.css" rel="stylesheet">
<title>Students</title>
</head>
<body>
    <form action = "/student-app/login" method = "POST">
      <div><span> Login: </span><input type="text" name = "name"></div>
      <div><span> Password: </span><input type="text" name = "password"></div>
      <div>
        <input type="submit" value="OK">
        <input type="reset" value="Clean">
      </div>
    </form>
</body>
</html>