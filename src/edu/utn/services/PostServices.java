package edu.utn.services;

import edu.utn.entity.EntityList;
import edu.utn.entity.User;
import edu.utn.entity.UserPost;
import edu.utn.enums.Result;
import edu.utn.factory.UserManagerFactory;
import edu.utn.factory.UserPostManagerFactory;
import edu.utn.manager.PostManager;
import edu.utn.manager.UserManager;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("post")
public class PostServices {

    @POST
    @Path("newPost")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newPost (String body){

        PostManager manager = UserPostManagerFactory.create();
        JSONObject jsonObject = new JSONObject(body);

        String email = jsonObject.getString("email");
        String post = jsonObject.getString("post");

        UserManager userManager = UserManagerFactory.create();
        User user = userManager.get(email);

        boolean value = manager.newPost(post, user.getId());
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }

        JSONObject jsonResult = new JSONObject(result);
        return Response.status(Response.Status.OK).entity(jsonResult.toString()).build();
    }

    //Este metodo obtiene todos los posteos del usuario, tanto como los propios, como de los amigos.
    @GET
    @Path("myPosts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String myPosts (String body){

        PostManager manager = UserPostManagerFactory.create();
        JSONObject jsonObject = new JSONObject(body);

        String email = jsonObject.getString("email");

        UserManager userManager = UserManagerFactory.create();
        User user = userManager.get(email);

        //Obtengo los nombres y apellidos de los usuarios de mis publicaciones
        List<UserPost> posts = manager.myPosts(user.getId());

        JSONArray jsonArray = new JSONArray();
        jsonObject = null;
        user = null;

        for(UserPost postElement : posts){
            jsonObject = new JSONObject();
            user = userManager.get(postElement.getIdUser());//Busco los datos del usuario que hizo la publicacion
            jsonObject.put("idPost", postElement.getId());
            jsonObject.put("user", user.getName() + " " + user.getSurname());
            jsonObject.put("date", postElement.getDatePublication());
            jsonObject.put("post", postElement.getPost());
            jsonArray.put(jsonObject);
        }

        EntityList entityList = new EntityList();
        entityList.setList(jsonArray);

        JSONObject response = new JSONObject(entityList);

        return  response.toString();
    }












}
