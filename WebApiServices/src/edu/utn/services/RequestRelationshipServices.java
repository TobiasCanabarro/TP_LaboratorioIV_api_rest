package edu.utn.services;

import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.manager.RequestRelationshipManager;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("request")
public class RequestRelationshipServices {

    @POST
    @Path("sendRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String sendRequest (String body){
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        JSONObject jsonObject = new JSONObject(body);
        boolean value = manager.sendRequest(jsonObject.getString("receive"), jsonObject.getString("send"));

        JSONObject response = new JSONObject(value);
        return response.toString();
    }

    @POST
    @Path("acceptRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String acceptRequest (String body){
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        JSONObject jsonObject = new JSONObject(body);
        boolean value = manager.acceptRequest(Long.valueOf(jsonObject.getString("idUser")));

        JSONObject response = new JSONObject(value);
        return response.toString();
    }

    @POST
    @Path("refuseRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String refuseRequest (long idRequest){
        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
        boolean value = manager.acceptRequest(idRequest);

        JSONObject response = new JSONObject(value);
        return response.toString();
    }

}
