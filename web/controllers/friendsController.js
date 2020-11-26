

var getMyFriends = ()=>{

    var request = new XMLHttpRequest();

    let body = {};
    body.email = localStorage.getItem("email");

    request.open('POST','rest/friend/myFriends', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = () => response(request);

    request.send(JSON.stringify(body));
}


var response=(request)=>{

    var object = request.responseText;
    var json = JSON.parse(object);
    var i = 0;
    if(object){
        localStorage.setItem('FriendList', json);
    }

    tbody = document.querySelector('#friendsTable tbody');
    tbody.innerHTML = '';

    console.log('Mi lista de amigos', json);

    json.list.forEach(e=>{
        var row = tbody.insertRow(i),
            name = row.insertCell(0),
            surname = row.insertCell(1),
            birthday = row.insertCell(2),
            selectUser = row.insertCell(3);

        name.innerHTML = e.name;
        surname.innerHTML = e.surname;
        birthday.innerHTML = e.birthday;


        var inputSelectUser = document.createElement('input');
        inputSelectUser.type= 'button';
        inputSelectUser.value = 'Delete Friendship';
        inputSelectUser.id = e.idUser;
        //inputSelectUser.onclick = "formUsers(btnSendUsers)";

        inputSelectUser.onclick = function formUsers() {
            var resUser = {};
            var id = inputSelectUser.id;

            resUser.result = deleteRelationship(id);
        };

        selectUser.appendChild(inputSelectUser);

        tbody.appendChild(row);
        i++;
    })
    i = 0;
}

var deleteRelationship = (id)=>{

    var body ={};
    body.receiveUserId = id;
    body.sendUserId = localStorage.getItem("myUserId");
    console.log(body);

    var request = new XMLHttpRequest();

    request.open('POST','rest/friend/deleteRelation', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => responseDelete(request);
    request.send(JSON.stringify(body));

}

var responseDelete = (request)=>{

    var object = request.responseText;
    var json = JSON.parse(object);
    alert(json.description);
    getMyFriends();
    console.log(json.description);
}

// function formUsers(btnSendUsers) {
//     var resUser = {};
//     var email = btnSendUsers.id;
//
//     resUser.result = SendRequest(email);
//
//     if (resUser.result == 'Ok') {
//         ui.correct();
//     } else {
//         ui.danger();
//     }
// }

getMyFriends();