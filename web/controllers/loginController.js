
let isLogin = () => {

    let login = localStorage.getItem("email");
    if(login != null){
        window.location.href = "home.html";
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
	console.log('myBody', body);
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/login', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => response(request);
    request.send(JSON.stringify(body));
}

let response = (request) => {

    let object = request.responseText;
    let json = JSON.parse(object);
    console.log(json);

    if(request.status == 200){
        localStorage.setItem("myUserId", json.user.id);
        window.location.href = "home.html";
    }
    else {
        alert(json.description);
    }
}

let loadHomeData = () => {

}

// let response = (request) => {
//
//     let object = request.responseText;
//
//     if(object){
//         let json = JSON.parse(object);
//         if(json.description == "Log in successful"){//Reemplazar por ok, fail o algo asi (un objecto)
//             window.location.href = "home.html";
//         }
//         else {
//             alert(json.description);
//         }
//     }
// }

let toHome = ()=>{
    window.location.href = "home.html";
}









