function ajaxRequest(request, url, json, onload){
	var xhr = new XMLHttpRequest();
    xhr.open(request, url, true);
    if(request=='POST' || request=='PUT'){
    	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json); 
    } else {
    	xhr.send()
    }
    xhr.onreadystatechange = function(){
  	    if (xhr.readyState != 4) return;  
  	    if (xhr.status != 200) {
  	        alert( xhr.status + ': ' + xhr.statusText ); 
  	    } else {
            onload();
  	    }
    }
}

function formUrl(entities){
	return "/student-app/" + entities;
}

function formUrlWithId(entities, id){
	return formUrl(entities) +"/" + id;
}

function addParam(field, value){
	return field+"="+value+"&";
}