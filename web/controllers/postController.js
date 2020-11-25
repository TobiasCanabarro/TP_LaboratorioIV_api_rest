

let postObject = () => {

    let obj = {};
    obj.email = localStorage.getItem("email");
    obj.post = document.getElementById("post").value;
    return obj;
}

let requestPost = () => {

    const body = postObject();

    let request = new XMLHttpRequest();

    request.open('POST', 'rest/post/newPost', true);
    request.setRequestHeader('Content-Type', 'application/json');

    request.onload=()=> responsePost(request);

    request.send(JSON.stringify(body));
}

let responsePost = (request) => {

    let obj = request.responseText;

    if(obj){

        document.getElementById("post").value = "";
        let json = JSON.parse(obj);

        if(!json.description == "Ok"){////Reemplazar por ok, fail o algo asi (un objecto)
            alert(json.description);
        }
    }
}



