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
    <form action="/student-app/students" method = "GET">
      <p>Filter</p>
      <div><span> First name: </span><input type="text" name = "filterFirstName"></div>
      <div><span> Second name: </span><input type="text" name = "filterSecondName"></div>
      <div><span> Group: </span><input type="text" name = "filterGroup"></div>
      <div><input type="submit" value="OK"></div>
    </form>
    <form action = "/student-app/students" method = "POST">
      <div><span> First name: </span><input type="text" name = "firstName"></div>
      <div><span> Second name: </span><input type="text" name = "secondName"></div>
      <div>
        <input type="submit" value="OK">
        <input type="reset" value="Clean">
      </div>
    </form>
    <table id="table">
      <thead>
        <tr>
          <th> First Name </th>
          <th> Second Name </th>
          <th> Group </th>
        </tr>
      </thead>
    </table>
    
    <script type="text/javascript">
      var data;
      var xhr = new XMLHttpRequest();
      xhr.open('GET', '/student-app/students', true);
      xhr.send(); 
      xhr.onreadystatechange = function(){
    	if (xhr.readyState != 4) return;  
    	  
        if (xhr.status != 200) {
    	  alert("ERROR");
    	  alert( xhr.status + ': ' + xhr.statusText ); 
    	} else {
          data = JSON.parse( xhr.responseText);
          var table = document.getElementById('table'); 
          table.insertAdjacentHTML( 'beforeend', createTable(data) );
    	}
      }
    
       
       function createTable(data) {
           var rowData;
           var rowHTML;
           var tableHTML = "<tbody>";

           for (var i = data.length - 1; i >= 0; i--) {
               rowData = data[i];
               rowHTML = "<tr>";
               rowHTML += "<td>" + rowData.firstName + "</td>";
               rowHTML += "<td>" + rowData.secondName + "</td>";
               rowHTML += "</tr>";
               tableHTML += rowHTML;
           }

           tableHTML += "</tbody>";
           return tableHTML;
       }
    </script>
    
</body>
</html>