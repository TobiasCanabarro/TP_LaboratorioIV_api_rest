package edu.utn.services;

import edu.utn.entity.Login;
import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.UserFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.log.LogHelper;
import edu.utn.mail.Mail;
import org.json.JSONArray;
import org.json.JSONObject;
import edu.utn.manager.UserManager;
import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Path("getUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(String body){
        UserManager manager = UserManagerFactory.create();
        JSONObject jsonObject = new JSONObject(body);
        User user = manager.get(jsonObject.getString("email"));

        JSONObject response = new JSONObject(user);
        return response.toString();
    }

    @GET
    @Path("getAllUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUser(){

        UserManager manager = UserManagerFactory.create();
        List<User> users = manager.getAllUser();

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;

        for (User user : users){
            jsonObject = new JSONObject();
            jsonObject.put("idUser", user.getId());
            jsonObject.put("name", user.getName());
            jsonObject.put("surname", user.getSurname());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("nickname", user.getNickname());
            jsonObject.put("birthday", user.getBirthday());
            jsonObject.put("attemptLogIn", user.getAttemptLogin());
            jsonObject.put("locked", user.isLocked());
            jsonObject.put("logIn", user.isLogIn());
            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }


    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logIn(String body) {
        System.out.println(body);
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
    @Path("signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String singIn(String body) throws MessagingException {

        JSONObject jsonObject = new JSONObject(body);
        User user = UserFactory.create(jsonObject);

        UserManager manager =  UserManagerFactory.create();
        boolean value = manager.signIn(user);
        Result result = Result.SIGN_IN_FAIL;

        if(value){
            Mail.sendMail(user.getEmail(), Result.SIGN_IN_OK, "Gracias por registrarse!");
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
        User user = manager.get(email);

        if(user != null){
            try {
                Mail.sendMail(email, Result.RESET_PASSWORD, "http://localhost:8080/webapi/resetPassword.html");
            }
            catch (MessagingException exception){
                LogHelper.createNewErrorLog(exception.getMessage());
            }
        }
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
           try {
               result = Result.CHANGE_PASSWORD;
               Mail.sendMail(email, result, "Se cambio la contrasenia");
           }
           catch (MessagingException exception){
               LogHelper.createNewErrorLog(exception.getMessage());
           }
        }

        JSONObject response = new JSONObject(result);
        return  response.toString();
    }








}
