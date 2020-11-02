package edu.utn.services;

import edu.utn.entity.Login;
import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.UserManagerFactory;
import edu.utn.mail.Mail;
import org.json.JSONObject;
import edu.utn.manager.UserManager;

//import javax.jws.soap.SOAPBinding;
import javax.mail.MessagingException;
import javax.ws.rs.*;
//import com.google.gson.Gson;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.List;

@Path("login")
public class UserServices {


    @GET
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(String id){

        JSONObject response = new JSONObject("user");
        return response.toString();
    }

    @GET
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUser(){
        JSONObject response = new JSONObject("user");
        UserManager manager = UserManagerFactory.create();
        List<User> users = manager.getAllUser();
        if(!users.isEmpty()){
            
        }

        return response.toString();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logIn(String body){

        JSONObject req = new JSONObject(body);
        String email = req.getString("email");
        String pass = req.getString("password");
        Login login = new Login();
        login.setEmail(email);
        login.setPassword(pass);

        JSONObject response = new JSONObject(login);
        return response.toString();
    }

    @POST
    @Path("singin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String singIn(String body) throws MessagingException {
        boolean retVal= false;
        JSONObject req = new JSONObject(body);
        String name = req.getString("name");
        String pass = req.getString("password");
        String surname = req.getString("surname");
        String email = req.getString("email");
        String nickname = req.getString("nickname");
        //Date birthday = Date.valueOf(req.getString("birthday"));

        User user = new User(name,pass,surname,email,nickname,new Date(90000000));

        UserManager manager =  UserManagerFactory.create();
        retVal = manager.signIn(user);
        if(retVal){
            Mail.sendMail(user.getEmail(), Result.SIGN_IN_OK, "endpoint: ");
        }

        JSONObject response = new JSONObject(retVal);
        return response.toString();
    }

    @POST
    @Path("singOut")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String singOut(String id){

        JSONObject response = new JSONObject("singout");
        return response.toString();
    }

    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logOut(String id){

        JSONObject response = new JSONObject("logout");
        return response.toString();
    }
}
