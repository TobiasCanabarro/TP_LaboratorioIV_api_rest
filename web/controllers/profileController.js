

let loadProfileObject = () => {
    let obj = {};
    obj.email = localStorage.getItem("email");
    return obj;
}

let requestLoadProfile = () => {

    let body = loadProfileObject();
    console.log('myBody', body);
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/getUser');
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => response(request);
    request.send(JSON.stringify(body));
}


function response (request) {
    let object = request.responseText;
    let myUser = JSON.parse(object);

    if(myUser != undefined){
        localStorage.setItem('myUser', myUser);
        console.log(myUser);
        document.getElementById("nameUser").innerHTML = myUser.name;
        document.getElementById("surname").innerHTML = myUser.surname;
        document.getElementById("nickname").innerHTML = myUser.nickname;
        document.getElementById("birthday").innerHTML = myUser.birthday;
        document.getElementById("email").innerHTML = myUser.email;
    }
}

requestLoadProfile();