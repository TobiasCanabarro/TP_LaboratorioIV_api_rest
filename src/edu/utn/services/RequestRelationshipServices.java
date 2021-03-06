package edu.utn.services;

import edu.utn.entity.EntityList;
import edu.utn.entity.RequestRelationship;
import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.factory.UserFactory;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.RequestRelationshipManager;
import edu.utn.manager.UserManager;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("friend")
public class RequestRelationshipServices {


    //Este metodo se usar para enviar una solicitud de amistad a otro usuario
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
        }

        JSONObject response = new JSONObject(result);
        return response.toString();
    }

    //Este metodo se usa para aceptar una solicitud
    @POST
    @Path("acceptRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String acceptRequest (String body){

        JSONObject jsonObject = new JSONObject(body);

        long receiveUserId = jsonObject.getLong("receiveUserId");
        long sendUserId = jsonObject.getLong("sendUserId");

        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();

//        RequestRelationship relationship = manager.get(sendUserId, receiveUserId);
        RequestRelationship relationship = manager.get(receiveUserId, sendUserId);

        boolean value = manager.acceptRequest(relationship.getId());
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }

        JSONObject response = new JSONObject(result);
        return response.toString();
    }

    //Este metodo se usa para rechazar una solicitud
    @POST
    @Path("refuseRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String refuseRequest (String body){

        JSONObject jsonObject = new JSONObject(body);
//        long idRequest = jsonObject.getLong("idRequest");

        long receiveUserId = jsonObject.getLong("receiveUserId");
        long sendUserId = jsonObject.getLong("sendUserId");

        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();

//        RequestRelationship relationship = manager.get(sendUserId, receiveUserId);
        RequestRelationship relationship = manager.get(receiveUserId, sendUserId);

        boolean value = manager.refuseRequest(relationship.getId());
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }

        JSONObject response = new JSONObject(result);
        return response.toString();
    }

    //Este metodo se usa para eliminar a un amigo
    @POST
    @Path("deleteRelation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRelation (String body) {

        JSONObject jsonObject = new JSONObject(body);

        long receiveUserId = jsonObject.getLong("receiveUserId");
        long sendUserId = jsonObject.getLong("sendUserId");

        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();

        RequestRelationship relationship = manager.get(receiveUserId, sendUserId);

        boolean value = manager.deleteRelationship(relationship.getId());
        Result result = Result.ERR;

        if(value){
            result = Result.OK;
        }

        JSONObject response = new JSONObject(result);

        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }

    @POST
    @Path("myFriends")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String myFriends (String body) {

        JSONObject jsonObject = new JSONObject(body);
        long id = jsonObject.getLong("id");

        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();

        List<User> myFriends = manager.myFriends(id);

        JSONArray jsonArray = UserFactory.create(myFriends);

        EntityList list = new EntityList();
        list.setList(jsonArray);

        JSONObject response = new JSONObject(list);

        return response.toString();
    }

    @POST
    @Path("myRequests")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response myRequests (String body) {

        JSONObject jsonObject = new JSONObject(body);
        long id = jsonObject.getLong("id");//mi ID

        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        UserManager userManager = UserManagerFactory.create();

        List<RequestRelationship> myRequest = manager.getAllRequest(id);
        List<User> users = new ArrayList<>();
        User user = null;

        for (RequestRelationship relationship : myRequest) {
            user = userManager.get(relationship.getIdUserSend());
            users.add(user);
        }

        JSONArray jsonArray = new JSONArray(users);

        EntityList entityList = new EntityList();
        entityList.setList(jsonArray);

        JSONObject response = new JSONObject(entityList);
        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }

}
