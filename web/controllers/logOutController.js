

let logOutObject = () => {

    let obj = {};
    obj.email = localStorage.getItem("email");
    localStorage.clear();

    return obj;
}

let requestLogOut = () => {

    const body = logOutObject();
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/logOut', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload=()=> responseLogOut();
    request.send(JSON.stringify(body));
}


let responseLogOut = () => {
    window.location.href = "index.html";
}



