

let loadProfileObject = () => {
    let obj = {};
    obj.id = localStorage.getItem("myUserId");
    return obj;
}

let requestLoadProfile = () => {

    let body = loadProfileObject();
    console.log('myBody', body);
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/getUser');
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => responseLoadProfile(request);
    request.send(JSON.stringify(body));
}


function responseLoadProfile (request) {


    if(request.status == 200){
        let object = request.responseText;

        if(object){
            let myUser = JSON.parse(object);
            if(myUser != undefined){

                localStorage.setItem('myUser', myUser);

                document.getElementById("nameUser").value = myUser.name;
                document.getElementById("surname").value = myUser.surname;
                document.getElementById("nickname").value = myUser.nickname;
                document.getElementById("birthday").value = myUser.birthday;
                document.getElementById("email").value = myUser.email;
            }
        }

    }


}

//**********************************

let changeObject = () => {

    let obj = {};
    obj.id = localStorage.getItem("email");
    obj.name = document.getElementById("nameUser").value;
    obj.surname = document.getElementById("surname").value;
    obj.nickname = document.getElementById("nickname").value;
    obj.email = document.getElementById("email").value;
    obj.birthday = document.getElementById("birthday").value;

    return obj;
}

let requestSaveChange = () => {

    let body = changeObject();
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/modifyProfile');
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => response(request);
    request.send(JSON.stringify(body));

}

let response = (request) => {

    let obj = request.responseText;

    if(request.status == 200){
        if(obj){
            let body = JSON.parse(obj);
            alert(body.description);
        }
    }

}


//requestLoadProfile();