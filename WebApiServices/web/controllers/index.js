

var request = ()=>{
    
    var body = logIn();
	
	console.log('myBody', body);
	
    var request = new XMLHttpRequest();
    
    // request.open('POST','http://localhost:8080/webapi/login/login', true);
    request.open('POST', 'http://localhost:8080/webapi/rest/login/login');

    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = () => response(request);
    request.send(body);
}

var response = (request)=>{
    var object = request.responseText;
	console.log('myResponse' , object);
    if(object){
        localStorage.setItem('myResponse', object);
    }
}


var logIn = ()=>{
    var obj = {};
    
    obj.email = document.querySelector(".un").value;
    obj.pass = document.querySelector(".pass").value;
    console.log("mi objeto ", obj);
    
    return obj; 
}