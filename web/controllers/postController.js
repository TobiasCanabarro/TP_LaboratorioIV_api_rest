

    let postObject = () => {

        let obj = {};
        obj.id = localStorage.getItem("myUserId");
        obj.post = document.getElementById("post").value;
        document.getElementById("post").value = "";
        return obj;
    }

    let requestPost = () => {

        const body = postObject();

        let request = new XMLHttpRequest();

         if(body.post != ""){
             request.open('POST', 'rest/post/newPost', true);
             request.setRequestHeader('Content-Type', 'application/json');

             request.onload=()=> responsePost(request);

             request.send(JSON.stringify(body));
         } else {
             alert("La publicacion no puede estar vacio");
         }


    }

    let responsePost = (request) => {

        if(request.status == 200){

            let obj = request.responseText;

            if(obj){

                let json = JSON.parse(obj);

                if(json.description != "OK"){
                    alert(json.description);
                }
                refresh();
                // getListPost();
            }
        }
    }


    let getListPost = ()=>{
        const body = {}
        body.id = localStorage.getItem('myUserId');

        let request = new XMLHttpRequest();
        request.open('POST','rest/post/myPosts', true);
        request.setRequestHeader('Content-Type', 'application/json');

        request.onload=()=> responseListPosts(request);

        request.send(JSON.stringify(body));
    }

    let responseListPosts = (request) =>{

        const listPost = [];
        let object = request.responseText;
        console.log(object);

        if(request.status == 200){

            let object = request.responseText;

            if(object){

                let json = JSON.parse(object);
                localStorage.setItem('postsList', json);

                orderListPost(json.list).forEach(e=>{
                    postByElement(e);
                })

                console.log(json.description);
                // getListPost();
                // location.reload();
            }
        }

    }

    var orderListPost =(array)=>{
       return array.sort((a,b)=>b.idPost-a.idPost);
    }

    var postByElement = (myElement)=>{

        var post = document.createElement("ARTICLE");
        post.innerHTML = '';

        var nameUser = document.createElement('H5');
        nameUser.innerHTML = myElement.user;
        post.appendChild(nameUser);

        var img = document.createElement('IMG');
        // img.width="200";
        // img.height="300";
        img.src = "img/eldiego.png";

        post.appendChild(img);

        var parrafo = document.createElement('P');
        parrafo.type= 'text';
        parrafo.innerHTML = myElement.post;
        post.appendChild(parrafo);

        var time = document.createElement('TIME');
        time.datetime = 'YYYY-MM-DD';
        time.innerHTML = myElement.date;
        post.appendChild(time);

        document.getElementById('postsList').appendChild(post);
    }

    getListPost();


    let refresh = ()=> {

        window.location.href = "home.html";

    }