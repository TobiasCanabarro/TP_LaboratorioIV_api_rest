package edu.utn.services;

import edu.utn.entity.UserPost;
import edu.utn.factory.UserPostManagerFactory;
import edu.utn.helper.DateHelper;
import edu.utn.manager.PostManager;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

//@Path("post")
public class PostServices {

//    @POST
//    @Path("newPost")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String newPost (String body){
//        PostManager manager = UserPostManagerFactory.create();
//        JSONObject jsonObject = new JSONObject(body);
//
//        boolean value = manager.newPost(jsonObject.getString("post"), Long.valueOf(jsonObject.getString("idUser")));
//
//        JSONObject result = new JSONObject(value);
//        return result.toString();
//    }
//
//    @GET
//    @Path("myPosts")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String myPosts (String body){
//        PostManager manager = UserPostManagerFactory.create();
//        JSONObject jsonObject = new JSONObject(body);
//
//        List<UserPost> posts = manager.myPosts(Long.valueOf(jsonObject.getString("idUser")));
//        JSONArray jsonArray = new JSONArray();
//        int i = 1;
//        for (UserPost element : posts){
//            jsonArray.put(i++, element);
//        }
//
//        return  jsonArray.toString();
//    }

}
