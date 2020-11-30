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
    public Response getUser(String body){

        JSONObject jsonObject = new JSONObject(body);
        long id = jsonObject.getLong("id");

        UserManager manager = UserManagerFactory.create();
        User user = manager.get(id);

        JSONObject response = new JSONObject(user);

        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }

    //Este metodo trae todos los usuarios registrados de la base de datos
    @GET
    @Path("getAllUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser(){

        UserManager manager = UserManagerFactory.create();
        List<User> users = manager.getAllUser();

        JSONArray jsonArray = UserFactory.create(users);

        EntityList userList = new EntityList();
        userList.setList(jsonArray);

        JSONObject response = new JSONObject(userList);
        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }


    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(String body) {

        JSONObject jsonObject = new JSONObject(body);
        UserManager manager = UserManagerFactory.create();

        String email = jsonObject.getString("email");

        User user = manager.getValidator().isAlreadyLogin(email);//Se fija si el usuario ya esta logeado, en caso que este, devuelve el usuario.
        Result result = Result.ERR;

        if (user != null) {
            result = Result.OK;
            result.setUser(user);
        }
        else {
            Login login = LoginFactory.create(jsonObject);
            result = manager.logIn(login.getEmail(), login.getPassword());
        }

        JSONObject response = new JSONObject(result);

        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }


    @POST
    @Path("signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response singIn(String body) {

        JSONObject jsonObject = new JSONObject(body);
        User user = UserFactory.create(jsonObject);

        UserManager manager =  UserManagerFactory.create();
        boolean value = manager.signIn(user);
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }

        JSONObject response = new JSONObject(result);

        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }

    @POST
    @Path("logOut")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logOut(String email){

        JSONObject jsonObject = new JSONObject(email);
        long id = jsonObject.getLong("id");

        UserManager manager = UserManagerFactory.create();
        User user = manager.get(id);

        boolean value = manager.logOut(user.getId());

        Result resultLogOut = Result.ERR;
        if(value){
            resultLogOut = Result.OK;
        }

        JSONObject response = new JSONObject(resultLogOut);

        return Response.status(Response.Status.OK).entity(response.toString()).build();
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
    public Response unLockedAccount (String body) {

        JSONObject jsonObject = new JSONObject(body);

        UserManager manager = UserManagerFactory.create();
        boolean value =  manager.unLockedAccount(jsonObject.getString("email"));

        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }

        JSONObject response = new JSONObject(result);
        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }


    //Con este endpoint se hace la solicitud para recuperar la cuenta
    @POST
    @Path("forgotPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response forgotPassword (String body) {

        JSONObject jsonObject = new JSONObject(body);
        String email = jsonObject.getString("email");

        UserManager manager = UserManagerFactory.create();
        boolean value = manager.forgotPassword(email);
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }

        JSONObject response = new JSONObject(result);
        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }

    //Reinicia la contrasena. Toma la nueva contrasena del input del html
    @POST
    @Path("resetPassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPassword (String body){

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
        return  Response.status(Response.Status.OK).entity(response.toString()).build();
    }

    //Este metodo te permite modificar los datos del usuario.
    @POST
    @Path("modifyProfile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyProfile(String body){

        JSONObject jsonObject = new JSONObject(body);

        ProfileValidator validator = new ProfileValidator();
        UserManager manager = UserManagerFactory.create();

        User userWithoutChange = manager.get(jsonObject.getString("id"));//El id en este caso es el email
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
