<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored = "false" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/js/functions.js" rel="stylesheet" type="text/javascript">
<title>Students</title>
</head>
<body>
    <form onsubmit="updateTable(event)">
      <p>Filter</p>
      <div><span> First name: </span><input type="text" name = "filterFirstName"></div>
      <div><span> Second name: </span><input type="text" name = "filterSecondName"></div>
      <div><span> Group: </span><input type="text" name = "filterGroup"></div>
      <div><input type="submit" value="OK"></div>
    </form>
    <form onsubmit="addEntity(event)">
      <div><span> First name: </span><input type="text" name = "firstName"></div>
      <div><span> Second name: </span><input type="text" name = "secondName"></div>
      <div>
        <input type="submit" value="OK">
        <input type="reset" value="Clean">
      </div>
    </form>
    <table id="table" class = "table table-bordered">
      <thead>
        <tr>
          <th> First Name </th>
          <th> Second Name </th>
          <th> Group </th>
          <th> Delete </th>
          <th> Update </th>
        </tr>
      </thead>
      <tbody id="tbody">
      </tbody>
    </table>
    
    <div id="updatePopup" class="modal">
      <div class="modal-content">
       <form onsubmit="updateEntity(event)">
        <span class="close" onclick="hidePopup()">&times;</span>
        <input type="hidden" name = "updateId">
        <div><span> First name: </span><input type="text" name = "updateFirstName"></div>
        <div><span> Second name: </span><input type="text" name = "updateSecondName"></div>
        <div><span> Group: </span><input type="text" name = "updateGroup"></div>
        <div>
          <input type="submit" value="Save">
        </div> 
      </form>
     </div> 
    </div>
    
    <script type="text/javascript">
      
      var popup = document.getElementById('updatePopup'); 
     
      function formUrlWithFilter(){
    	  var firstName = document.getElementsByName('filterFirstName')[0].value;
    	  var secondName = document.getElementsByName('filterSecondName')[0].value;
    	  var groupId = document.getElementsByName('filterGroup')[0].value;
    	  return "/student-app/students?" + addParam('filterFirstName', firstName)+
    	        addParam('filterSecondName', secondName) +
    	        addParam('filterGroup', groupId);
      }
      
      function formJsonForPost(){
    	  var firstName = document.getElementsByName('firstName')[0].value;
    	  var secondName = document.getElementsByName('secondName')[0].value;
    	  
    	  return JSON.stringify({
    		  firstName: firstName,
    		  secondName: secondName
    	  });
      }
      
      function formJsonForPut(){
    	  var id = document.getElementsByName('updateId')[0].value;
    	  var newFirstName = document.getElementsByName('updateFirstName')[0].value;
   	      var newSecondName = document.getElementsByName('updateSecondName')[0].value;
	      var newGroupId = document.getElementsByName('updateGroup')[0].value;
	      
	      return JSON.stringify({
	    	  id: id,
    		  firstName: newFirstName,
    		  secondName: newSecondName,
    		  group: {
    			  id: newGroupId
    		  }
    	  });
      }
      
      function updateTable(e){
    	  if(e){
    		  e.preventDefault();
    	  }
    		  
    	  ajaxRequest('GET', formUrlWithFilter(), null, new function(){
    		  var data = JSON.parse(xhr.responseText);
              var table = document.getElementById('tbody'); 
              table.innerHTML = createTable(data);
    	  });
      }
      
      function addEntity(e){
    	  if(e){
    		  e.preventDefault();
    	  }
    	  
          ajaxRequest('POST', formUrl("students"), formJsonForPost(), new function(){
        	  updateTable(e);
          });
          
      }
      
      function deleteEntity(e, id){
    	  if(e){
    		  e.preventDefault();
    	  }
    	  
    	  ajaxRequest('DELETE', formUrlWithId("students", id), null, new function(){
        	  updateTable(e);
          });
      }
      
      function updateEntity(e){
    	  if(e){
    		  e.preventDefault();
    	  }
    	  var id = document.getElementsByName('updateId')[0].value;
    	  ajaxRequest('PUT', formUrlWithId("students", id), formJsonForPut(), new function() {
    		  hidePopup();
              updateTable(e);
		  });
      }
       
      function createTable(data) {
           var rowData;
           var rowHTML;
           var tableHTML = "";

           for (var i = data.length - 1; i >= 0; i--) {
               rowData = data[i];
               currentPopupEntity = rowData;
               rowHTML = "<tr>";
               rowHTML += "<td>" + rowData.firstName + "</td>";
               rowHTML += "<td>" + rowData.secondName + "</td>";
               rowHTML += "<td>" + rowData.group.id + "</td>";
               rowHTML += "<td><input type=\"button\" onclick=\"deleteEntity(event, " + rowData.id +")\" value=\"-\"></td>"
               rowHTML += "<td><input type=\"button\" onclick=\"showPopup(" + rowData.id + ")\" value=\"-\"></td>";
               rowHTML += "</tr>";
               tableHTML += rowHTML;
           }

           tableHTML += "";
           return tableHTML;
       }
       
       function showPopup(id){
    	   var data;
    	   var xhr = new XMLHttpRequest();
           xhr.open('GET', "/student-app/students/"+id, true);
           xhr.send(); 
           xhr.onreadystatechange = function(){
         	if (xhr.readyState != 4) return;  
         	  
             if (xhr.status != 200) {
         	  alert("ERROR");
         	  alert( xhr.status + ': ' + xhr.statusText ); 
         	} else {
               data = JSON.parse(xhr.responseText);
               document.getElementsByName('updateId')[0].value = data.id;
        	   document.getElementsByName('updateFirstName')[0].value = data.firstName;
        	   document.getElementsByName('updateSecondName')[0].value = data.secondName;
        	   document.getElementsByName('updateGroup')[0].value = data.group.id;
        	   popup.style.display = "block";
         	}
           }
       }
       
       function hidePopup(){
    	   popup.style.display = "none";
       }
       
       updateTable();
    </script>
    
</body>
</html>