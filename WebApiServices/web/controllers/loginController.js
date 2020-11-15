

function logIn (){
    var obj = {};

    obj.email = document.querySelector(".un").value;
    obj.password = document.querySelector(".pass").value;

    return obj;
}

function requestLogin (){
    
    var body = logIn();
	console.log('myBody', body);
    var request = new XMLHttpRequest();

    request.open('POST', 'rest/login/login', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => response(request, body);
    request.send(JSON.stringify(body));

}

let response = (request, body) => {
    let object = request.responseText;

    if(object){
        let json = JSON.parse(object);
        if(json.description == "Log in successful"){
            localStorage.setItem("myUser", body);
            window.location.href = "inicio.html";
        }
        else {
            alert(json.description);
        }
    }

}




var toHome = ()=>{
    window.location.href = "inicio.html";
}