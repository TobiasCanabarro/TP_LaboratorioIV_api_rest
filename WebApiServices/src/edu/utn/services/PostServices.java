package edu.utn.services;

import edu.utn.entity.User;
import edu.utn.entity.UserPost;
import edu.utn.factory.UserManagerFactory;
import edu.utn.factory.UserPostManagerFactory;
import edu.utn.manager.PostManager;
import edu.utn.manager.UserManager;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("post")
public class PostServices {

    @POST
    @Path("newPost")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String newPost (String body){
        PostManager manager = UserPostManagerFactory.create();
        JSONObject jsonObject = new JSONObject(body);

        boolean value = manager.newPost(jsonObject.getString("post"), Long.valueOf(jsonObject.getString("idUser")));

        JSONObject result = new JSONObject(value);
        return result.toString();
    }

    @GET
    @Path("myPosts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String myPosts (String body){
        PostManager manager = UserPostManagerFactory.create();
        JSONObject jsonObject = new JSONObject(body);
        long idUser = Long.valueOf(jsonObject.getString("idUser"));
        List<UserPost> posts = manager.myPosts(idUser);

        //Obtengo los nombres y apellidos de los usuarios de mis publicaciones

        UserManager userManager = UserManagerFactory.create();
        JSONArray jsonArray = new JSONArray();
        JSONObject response = null;
        User user = null;

        for(UserPost postElement : posts){
            response = new JSONObject();
            user = userManager.get(postElement.getIdUser());
            response.put("idPost", postElement.getId());
            response.put("user", user.getName() + " " + user.getSurname());
            response.put("date", postElement.getDatePublication());
            response.put("post", postElement.getPost());
            jsonArray.put(response);
        }

        return  jsonArray.toString();
    }












}
