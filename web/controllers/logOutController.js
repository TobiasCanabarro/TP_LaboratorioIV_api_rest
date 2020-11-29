

let logOutObject = () => {

    let obj = {};
    obj.id = localStorage.getItem("myUserId");

    return obj;
}

let requestLogOut = () => {

    const body = logOutObject();
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/login/logOut', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload=()=> responseLogOut(request);
    request.send(JSON.stringify(body));
}


let responseLogOut = (request) => {

    if(request.status == 200){

        let obj = request.responseText;

        if(obj){

            let json = JSON.parse(obj);

            if(json.description == "OK"){
                localStorage.clear();
                window.location.href = "index.html";
            }
        }
    }






}



