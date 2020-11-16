

let signInObject = () => {
    let obj = {};
    obj.name = document.querySelector("#name").value;
    obj.surname = document.querySelector("#sur").value;
    obj.password = document.querySelector("#pass").value;
    obj.email = document.querySelector("#email").value;
    obj.nickname = document.querySelector("#nick").value;
    obj.birthday = document.querySelector("#bd").value;
    return obj;
}

var response = (request) => {
    var obj = request.responseText;
    var json = JSON.parse(obj);
    if(obj){
        if(json.description == "Sign in successful"){////Reemplazar por ok, fail o algo asi (un objecto)
            alert(json.description);
            window.location.href = "index.html";
        }
        else {
            alert(json.description);
        }

        localStorage.setItem('responseSignIn', obj);
    }
}

var requestSignIn = () => {
    const body = signInObject();
    console.log('myBody', body);
    var request = new XMLHttpRequest();
    request.open('POST', 'rest/login/signin', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.onload=()=> response(request);
    request.send(JSON.stringify(body));
}

