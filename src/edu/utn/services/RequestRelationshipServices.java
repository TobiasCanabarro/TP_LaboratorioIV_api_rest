package edu.utn.services;

import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.factory.UserFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.mail.Mail;
import edu.utn.manager.RequestRelationshipManager;
import edu.utn.manager.UserManager;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("friend")
public class RequestRelationshipServices {

    @POST
    @Path("sendRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String sendRequest (String body){

        JSONObject jsonObject = new JSONObject(body);
        String receiveEmail = jsonObject.getString("receiveEmail");
        String sendEmail = jsonObject.getString("sendEmail");

        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        boolean value = manager.sendRequest(receiveEmail, sendEmail);
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
            Mail.sendMail(receiveEmail, Result.SEND_REQUEST_OK, "El usuario " + sendEmail + " te envio una solicitud de amistad!");
        }

        JSONObject response = new JSONObject(result);
        return response.toString();
    }

    @POST
    @Path("acceptRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String acceptRequest (String body){
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        JSONObject jsonObject = new JSONObject(body);

        long idUser = jsonObject.getLong("idUser");
        boolean value = manager.acceptRequest(idUser);
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }


        JSONObject response = new JSONObject(result);
        return response.toString();
    }

    @POST
    @Path("refuseRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String refuseRequest (String body){

        JSONObject jsonObject = new JSONObject(body);
        long idRequest = jsonObject.getLong("idRequest");

        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        boolean value = manager.acceptRequest(idRequest);
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }

        JSONObject response = new JSONObject(result);
        return response.toString();
    }


    //TODO hacer el endpoint deleteRequest
    @POST
    @Path("deleteRelation")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteRelation (String body) {

        JSONObject jsonObject = new JSONObject(body);
        long idRelation = jsonObject.getLong("idRelation");

        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        boolean value = manager.deleteRelationship(idRelation);
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }

        JSONObject response = new JSONObject(result);

        return response.toString();
    }

    @POST
    @Path("myFriends")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String myFriends (String body) {

        JSONObject jsonObject = new JSONObject(body);
        String email = jsonObject.getString("email");

        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        UserManager userManager = UserManagerFactory.create();
        User user = userManager.get(email);

        List<User> myFriends = manager.myFriends(user.getId());

        JSONArray response = UserFactory.create(myFriends);

        return response.toString();
    }


}
