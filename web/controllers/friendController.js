
document.body.onload = prueba();

let myFriendObject = () => {
    let obj = {};
    obj.email = localStorage.getItem("email");
    return obj;
}


let myFriendsRequest = () => {
    const body = myFriendObject();
    let request = new XMLHttpRequest();

    request.open('POST', 'rest/friend/myFriends', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = () => responseMyFriends(request);
    request.send(JSON.stringify(body));
}



let responseMyFriends = (request) => {
    let obj = request.responseText;

    if(obj){

        // let newDiv = document.createElement("div");
        // let newContent = document.createTextNode("Pablo");
        // newDiv.appendChild(newContent);
        //
        // let currentDiv = document.getElementById("div1");
        // document.body.insertBefore(newDiv, newContent);

        // let json = JSON.parse(obj);
        // console.log(json);
        // alert(json.description);
    }
}
