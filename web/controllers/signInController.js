

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

let requestSignIn = () => {

    const body = signInObject();

    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/signin', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload=()=> response(request);
    request.send(JSON.stringify(body));
}

let response = (request) => {

   if(request.status == 200){

       let obj = request.responseText;

       if(obj){

           let json = JSON.parse(obj);

           if(json.description == "OK"){
               window.location.href = "index.html";
           }
           else {
               alert(json.description);
           }
       }
   }
}



