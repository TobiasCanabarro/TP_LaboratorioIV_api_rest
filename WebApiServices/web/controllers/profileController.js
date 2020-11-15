
let loadProfileObject = () => {
    let obj = {};
    obj.email = "tomas@gmail.com";
    return obj;
}


//getUser
let requestLoadProfile = () => {

    let body = loadProfileObject();
    console.log('myBody', body);
    let request = new XMLHttpRequest();

    request.open('GET', 'rest/login/getUser');
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => response(request);
    request.send(JSON.stringify(body));

}



function response (request) {
    let object = request.responseText;

    if(object){
        localStorage.setItem('responseLogIn', object);
        let myUser = JSON.parse(object);
        if(myUser != undefined){
           document.getElementById("nameUser").innerHTML = myUser.name;
        }
    }



}

requestLoadProfile();