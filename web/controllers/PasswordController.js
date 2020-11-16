
let forgotPasswordObject = () => {
    let obj = {};
    obj.email = document.querySelector("#email").value;
    localStorage.setItem("emailforgotpass",obj.email);
    return obj;
}

let requestForgotPassword = () => {
    const body = forgotPasswordObject();
    let request = new XMLHttpRequest();
    request.open('POST', 'rest/login/forgotPassword', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.send(JSON.stringify(body));
}

//************************************************************************************

let resetPasswordObject = () => {
    let obj = {};
    obj.password = document.querySelector('#pass').value;
    return obj;
}


let requestResetPassword = () => {
    const body = resetPasswordObject();
    let request = new XMLHttpRequest();
    request.open('POST', 'rest/login/resetPassword', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = () => response();
    request.send(JSON.stringify(body));
}


let response = (request) => {
    let obj = request.responseText;
    let json = JSON.parse(obj);
    if(obj){
        localStorage.setItem('responseForgotPass', obj);
        if(json.description == "Change password"){////Reemplazar por ok, fail o algo asi (un objecto)
            alert(json.description);
            window.location.href = 'index.html';
        }
        else {
            alert(json.description);
        }
    }
}