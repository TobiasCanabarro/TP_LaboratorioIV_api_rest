

var getRequestFriend = ()=>{

    let body = {};
    body.id = localStorage.getItem('myUserId');
    let request = new XMLHttpRequest();
    
    request.open('POST','rest/friend/myRequests', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.onload = () => response(request);

    request.send(JSON.stringify(body));    
}


var response=(request)=>{

    let object = request.responseText;
    var i = 0;
    console.log(object);

    if(object){
        var json = JSON.parse(object);
        localStorage.setItem('RequestFriendsList', json);
    }

    tbody = document.querySelector('#requestFriendsTable tbody');
    tbody.innerHTML = '';
    
    json.list.forEach(e=>{
        var row = tbody.insertRow(i),
        name = row.insertCell(0),
        surname = row.insertCell(1),
        birthday = row.insertCell(2),
        inputAccept = row.insertCell(3),
        inputRefuse = row.insertCell(4);

        name.innerHTML = e.name;
        surname.innerHTML = e.surname;
        birthday.innerHTML = e.birthday;


        var inputAcceptFriend = document.createElement('input');
		inputAcceptFriend.type= 'button';
        inputAcceptFriend.value = 'Accept Friendship';
        inputAcceptFriend.id = e.id;

        inputAcceptFriend.onclick = function (){
            var res = {};
            var id = inputAcceptFriend.id;

            res.result = acceptRelation(id);
        }

        inputAccept.appendChild(inputAcceptFriend);
        
        var inputRefuseFriend = document.createElement('input');
        inputRefuseFriend.type= 'button';
        inputRefuseFriend.value = 'Refuse Friendship';
        inputRefuseFriend.id = e.id;

        inputRefuseFriend.onclick = function () {
            var res = {};
            var id = inputAcceptFriend.id;

            res.result = refuseRelation(id);
        }

        inputRefuse.appendChild(inputRefuseFriend);

		tbody.appendChild(row);
        i++;
    });
    i = 0;
}

let refuseRelation = (id) => {

    let body = {};
    body.receiveUserId = id;
    body.sendUserId = localStorage.getItem("myUserId");

    let request = new XMLHttpRequest();

    request.open('POST','rest/friend/refuseRequest', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => responseRequest(request);
    request.send(JSON.stringify(body));
}


let acceptRelation = (id) => {

    let body = {};
    body.receiveUserId = id;
    body.sendUserId = localStorage.getItem("myUserId");

    let request = new XMLHttpRequest();

    request.open('POST','rest/friend/acceptRequest', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload = () => responseRequest(request);
    request.send(JSON.stringify(body));

}

let responseRequest = (request) =>{

    if(request.status == 200){

        let object = request.responseText;

        if(object){

            let json = JSON.parse(object);
            alert(json.description);
            getRequestFriend();
            console.log(json.description);
        }
    }
}