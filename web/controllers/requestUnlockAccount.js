
const TOKEN_REQUEST_UNLOCK_ACCOUNT = "requestUnlockAccount";

//const TOKEN_RESPONSE_UNLOCK_ACCOUNT = "responseUnlockAccount";

let requestUnlockAccountObject = () => {
    let obj = {};
    obj.email = document.querySelector("#email").value;
    localStorage.setItem(TOKEN_REQUEST_UNLOCK_ACCOUNT, obj.email);
    return obj;
}


let requestUnlockAccount = () => {

    const body = requestUnlockAccountObject();
    let request = new XMLHttpRequest();

    request.open('POST', "rest/login/requestUnlockedAccount" ,true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.send(JSON.stringify(body));
}

let unlockAccountObject = () => {
    let obj = {};
    obj.email = localStorage.getItem(TOKEN_REQUEST_UNLOCK_ACCOUNT);
    return obj;
}


let unlockAccount = () => {
    const body = unlockAccountObject();
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/unlockedAccount', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = () => responseUnlockAccount(request);
    request.send(JSON.stringify(body));
}


let responseUnlockAccount = (request) => {
    let obj = request.responseText;

    if(request.status == 200){
        if(obj){

            let json = JSON.parse(obj);
            alert(json.description);
        }
        window.location.href = "index.html";
    }


}