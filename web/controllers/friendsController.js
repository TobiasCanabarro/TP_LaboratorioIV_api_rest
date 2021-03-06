

    var getMyFriends = ()=>{

        var request = new XMLHttpRequest();

        let body = {};
        body.id = localStorage.getItem("myUserId");

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
        body.receiveUserId = localStorage.getItem("myUserId");
        body.sendUserId = id;
        console.log(body);

        var request = new XMLHttpRequest();

        request.open('POST','rest/friend/deleteRelation', true);
        request.setRequestHeader('Content-Type', 'application/json');

        request.onload = () => responseDelete(request);
        request.send(JSON.stringify(body));

    }

    let responseDelete = (request)=>{

        if(request.status == 200){

            let object = request.responseText;

            if(object){

                let json = JSON.parse(object);
                alert(json.description);
                location.reload();
                // getMyFriends();
                console.log(json.description);
            }
        }
    }

    // getMyFriends();