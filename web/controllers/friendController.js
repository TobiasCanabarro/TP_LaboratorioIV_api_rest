


var getAllUser = ()=>{

    var request = new XMLHttpRequest();
    
    request.open('GET','rest/login/getAllUser', true);
    request.onload = () => response(request);

    request.send();    
}


var response=(request)=>{    
    var object = request.responseText;
    var json = JSON.parse(object);
    var i = 0;
    if(object){
        localStorage.setItem('UsersList', json);
    }

    tbody = document.querySelector('#usersTable tbody');
    tbody.innerHTML = '';

    console.log('Mi lista de usuarios', json);

    json.userList.forEach(e=>{
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
		inputSelectUser.value = 'send Request';
		inputSelectUser.id = e.email;
        //inputSelectUser.onclick = "formUsers(btnSendUsers)";

        inputSelectUser.onclick = function formUsers() {
            var resUser = {};
            var email = inputSelectUser.id;

            resUser.result = SendRequest(email);
        };

        selectUser.appendChild(inputSelectUser);

		tbody.appendChild(row);
        i++;
    })
    i = 0;
}

var SendRequest = (email)=>{

    var body ={};
    body.receiveEmail = email;
    body.sendEmail = localStorage.getItem("email");

    var request = new XMLHttpRequest();

    request.open('POST','rest/friend/sendRequest', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = () => responseFriend(request);

    request.send(JSON.stringify(body));

}

var responseFriend = (request)=>{

    var object = request.responseText;
    var json = JSON.parse(object);
    alert(json.description);
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

getAllUser();