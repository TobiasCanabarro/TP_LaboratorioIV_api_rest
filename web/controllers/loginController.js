
let isLogin = () => {

    let login = localStorage.getItem("email");
    if(login != null){

        let body = {};
        body.email = login;

        let request = new XMLHttpRequest();

        request.open('POST', 'rest/login/login', true);
        request.setRequestHeader('Content-Type', 'application/json');

        request.onload = () => response(request);
        request.send(JSON.stringify(body));
    }else {
        console.log("No habia una sesion iniciada");
    }
}

function logInObject (){
    let obj = {};

    obj.email = document.querySelector(".un").value;
    obj.password = document.querySelector(".pass").value;
    localStorage.setItem("email", obj.email);

    return obj;
}

function requestLogin (){
    
    let body = logInObject();
	// console.log('myBody', body);
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/login', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => response(request);
    request.send(JSON.stringify(body));
}

let response = (request) => {

    let object = request.responseText;

    if(request.status == 200){

        let json = JSON.parse(object);
        console.log(json);

        if(json.description == "OK"){
            localStorage.setItem("myUserId", json.user.id);
            window.location.href = "home.html";
        }
        else {
            alert(json.description);
        }
    }

}

let loadHomeData = () => {

}












