package edu.utn.services;

import com.google.gson.Gson;
import edu.utn.entity.Login;
import edu.utn.entity.User;
import edu.utn.entity.UserPost;
import edu.utn.enums.Result;
import edu.utn.factory.UserFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.mail.Mail;

import org.json.JSONObject;
import edu.utn.manager.UserManager;
import org.omg.Dynamic.Parameter;

import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("login")
public class LoginServices {

    @GET
    @Path("algo")
    @Produces(MediaType.TEXT_PLAIN)
    public String getalgo(){

        return "tuhermana";
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(String body){
        UserManager manager = UserManagerFactory.create();
        JSONObject jsonObject = new JSONObject(body);
        User user = manager.get(Long.valueOf(jsonObject.getString("idUser")));

        JSONObject response = new JSONObject(user);
        return response.toString();
    }

    @GET
    @Path("getAllUser")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUser(){

        UserManager manager = UserManagerFactory.create();
        List<User> users = manager.getAllUser();

        //Gson gson = new Gson();

        return "gson.toJson(users)";
    }


    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logIn(String body) throws MessagingException {

        JSONObject req = new JSONObject(body);
        String email = req.getString("email");
        String pass = req.getString("password");

        Login login = new Login();
        login.setEmail(email);
        login.setPasword(pass);

        UserManager manager = UserManagerFactory.create();
        Result value = manager.logIn(login.getEmail(), login.getPasword());

        JSONObject response = new JSONObject(value);
        return response.toString();
    }

    @POST
    @Path("singin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String singIn(String body) throws MessagingException {

        JSONObject jsonObject = new JSONObject(body);
        User user = UserFactory.create(jsonObject);

        UserManager manager =  UserManagerFactory.create();
        boolean value = manager.signIn(user);
        Result result = Result.SIGN_IN_FAIL;

        if(value){
            Mail.sendMail(user.getEmail(), Result.SIGN_IN_OK, "endpoint: ");
            result = Result.SIGN_IN_OK;
        }

        JSONObject response = new JSONObject(result);
        return response.toString();
    }

    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logOut(String id){
        JSONObject req = new JSONObject(id);
        long idUser = Long.valueOf(req.getString("id_user"));
        UserManager manager = UserManagerFactory.create();
        boolean value = manager.logOut(idUser);

        Result resultLogOut = Result.LOG_OUT_FAIL;
        if(value){
            resultLogOut = Result.LOG_OUT_OK;
        }

        JSONObject response = new JSONObject(resultLogOut);
        return response.toString();
    }

    //Con este endpoint se solicita el desbloqueo de la cuenta
    @POST
    @Path("requestUnlockedAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public void requestUnlockedAccount (String body){
        JSONObject jsonObject = new JSONObject(body);
        UserManager manager = UserManagerFactory.create();
        String email = jsonObject.getString("email");
        manager.requestUnlockedAccount(email, "http:localhost:8080/webapi/login/unLockedAccount/" + email);

    }

    //Con este endpont se desbloquea la cuenta. Este endpoint llega por email
    @POST
    @Path("unLockedAccount/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public String unLockedAccount (@PathParam("email")String email) {
        UserManager manager = UserManagerFactory.create();
        boolean value =  manager.unLockedAccount(email);
        Result result = Result.UNLOCKED_ACCOUNT_FAIL;

        if(value){
            result = Result.UNLOCKED_ACCOUNT_OK;
        }

        JSONObject response = new JSONObject(result);
        return response.toString();
    }

}
