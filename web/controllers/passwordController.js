
let forgotPasswordObject = () => {
    let obj = {};
    obj.email = document.querySelector("#email").value;
    localStorage.setItem("forgotPasswordEmail", obj.email);
    return obj;
}

let requestForgotPassword = () => {

    const body = forgotPasswordObject();
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/forgotPassword', true);

    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = () => responseForgotPassword(request);
    request.send(JSON.stringify(body));
}

let responseForgotPassword = (request) => {

    if(request.status == 200){
        let obj = request.responseText;

        if(obj){

            let json = JSON.parse(obj);
            alert(json.description);

            if(json.description == "OK"){
                window.location.href = 'index.html';
            }
        }
    }
}

//************************************************************************************

let resetPasswordObject = () => {

    let obj = {};
    obj.password = document.querySelector('#pass').value;
    obj.email = localStorage.getItem("forgotPasswordEmail");
    return obj;
}


let requestResetPassword = () => {

    let password = document.querySelector('#pass').value;
    let confirmation = document.querySelector('#passCheck').value;

    if(equalsPassword(password, confirmation)){

        const body = resetPasswordObject();
        let request = new XMLHttpRequest();

        request.open('POST', 'rest/login/resetPassword', true);
        request.setRequestHeader('Content-Type', 'application/json');

        request.onload = () => response();
        request.send(JSON.stringify(body));
    }else {
        alert("las contraseñas no coinciden")
    }
}


let response = (request) => {

    if(request.status == 200){

        let obj = request.responseText;

        if(obj) {

            let json = JSON.parse(obj);
            alert(json.description);

            if(json.description == "OK") {
                window.location.href = 'index.html';
            }
        }
    }
}

let equalsPassword = (password, confirmation) => {
    return password == confirmation;
}