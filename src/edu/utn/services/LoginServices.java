package edu.utn.services;

import edu.utn.entity.EntityList;
import edu.utn.entity.Login;
import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.LoginFactory;
import edu.utn.factory.UserFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.validator.ProfileValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import edu.utn.manager.UserManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("login")
public class LoginServices {


    @POST
    @Path("getUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(String body){

        JSONObject jsonObject = new JSONObject(body);
        String email = jsonObject.getString("email");

        UserManager manager = UserManagerFactory.create();
        User user = manager.get(email);

        JSONObject response = new JSONObject(user);
        return response.toString();
    }

    @GET
    @Path("getAllUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUser(){

        UserManager manager = UserManagerFactory.create();
        List<User> users = manager.getAllUser();

        JSONArray jsonArray = UserFactory.create(users);

        EntityList userList = new EntityList();
        userList.setList(jsonArray);

        JSONObject jsonObject = new JSONObject(userList);
        return  jsonObject.toString();
    }

    //Response.status(Response.Status.OK).entity(response.toString()).build();
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(String body) {

        JSONObject jsonObject = new JSONObject(body);
        Login login = LoginFactory.create(jsonObject);

        UserManager manager = UserManagerFactory.create();
        Result value = manager.logIn(login.getEmail(), login.getPasword());

        JSONObject response = new JSONObject(value);
        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }


    @POST
    @Path("signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String singIn(String body) {

        JSONObject jsonObject = new JSONObject(body);
        User user = UserFactory.create(jsonObject);

        UserManager manager =  UserManagerFactory.create();
        boolean value = manager.signIn(user);
        Result result = Result.SIGN_IN_FAIL;

        if(value){
            result = Result.SIGN_IN_OK;
        }

        JSONObject response = new JSONObject(result);
        return response.toString();
    }

    @POST
    @Path("logOut")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logOut(String email){

        JSONObject jsonObject = new JSONObject(email);

        UserManager manager = UserManagerFactory.create();
        User user = manager.get( jsonObject.getString("email") );

        boolean value = manager.logOut(user.getId());

        Result resultLogOut = Result.ERR;
        if(value){
            resultLogOut = Result.OK;
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
        manager.requestUnlockedAccount(email, "http://localhost:8080/webapi/unlockAccount.html");
    }


    //Con este endpoint se desbloquea la cuenta. Este endpoint llega por email
    @POST
    @Path("unlockedAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String unLockedAccount (String body) {

        JSONObject jsonObject = new JSONObject(body);

        UserManager manager = UserManagerFactory.create();
        boolean value =  manager.unLockedAccount(jsonObject.getString("email"));

        Result result = Result.UNLOCKED_ACCOUNT_FAIL;

        if(value){
            result = Result.UNLOCKED_ACCOUNT_OK;
        }

        JSONObject response = new JSONObject(result);
        return response.toString();
    }


    //Con este endpoint se hace la solicitud para recuperar la cuenta
    @POST
    @Path("forgotPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public void forgotPassword (String body) {

        JSONObject jsonObject = new JSONObject(body);
        String email = jsonObject.getString("email");

        UserManager manager = UserManagerFactory.create();
        manager.forgotPassword(email);
    }

    //Reinicia la contrasena. Toma la nueva contrasena del input del html
    @POST
    @Path("resetPassword")
    @Produces(MediaType.APPLICATION_JSON)
    public String resetPassword (String body){

        JSONObject jsonObject = new JSONObject(body);
        String newPassword = jsonObject.getString("password");
        String email = jsonObject.getString("email");

        UserManager manager = UserManagerFactory.create();
        User user = manager.get(email);

        boolean value = manager.changePassword(user.getId(), newPassword);
        Result result = Result.CHANGE_PASSWORD_FAIL;

        if(value){
            result = Result.CHANGE_PASSWORD;
        }

        JSONObject response = new JSONObject(result);
        return  response.toString();
    }


    @POST
    @Path("modifyProfile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyProfile(String body){

        JSONObject jsonObject = new JSONObject(body);

        ProfileValidator validator = new ProfileValidator();
        UserManager manager = UserManagerFactory.create();

        User userWithoutChange = manager.get(jsonObject.getString("id"));
        User userWithChange = UserFactory.create(jsonObject, userWithoutChange);

        boolean value = validator.isValid(userWithoutChange, userWithChange);
        Result result = Result.ERR;

        if(value){
            manager.update(userWithChange);
            result = Result.OK;
        }

        JSONObject response = new JSONObject(result);

        return  Response.status(Response.Status.OK).entity(response.toString()).build();
    }




}
