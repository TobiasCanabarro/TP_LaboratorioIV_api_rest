

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
        document.getElementById("nameUser").value = myUser.name;
        document.getElementById("surname").value = myUser.surname;
        document.getElementById("nickname").value = myUser.nickname;
        document.getElementById("birthday").value = myUser.birthday;
        document.getElementById("email").value = myUser.email;
    }
}

//**********************************

let changeObject = () => {
    let obj = {};
    obj.name = document.getElementById("userName").value;
    obj.surname = document.getElementById("surname").value;
    obj.nickname = document.getElementById("nickname").value;
    obj.email = document.getElementById("email").value;
    obj.birthday = document.getElementById("birthday").value;
    return obj;
}

let requestSaveChange = () => {
    let body = changeObject();
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/changeProfile');
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => responseSaveChange(request);
    request.send(JSON.stringify(body));

}

let responseSaveChange = (request) = {

}


requestLoadProfile();