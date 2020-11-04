package edu.utn.services;

import edu.utn.factory.RequestRelationshipManagerFactory;
import edu.utn.manager.RequestRelationshipManager;
import org.json.JSONObject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@Path("request")
public class RequestRelationshipServices {

//    @POST
//    @Path("sendRequest")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String sendRequest (String receiveEmail, String sendEmail){
//        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
//        boolean value = manager.sendRequest(receiveEmail, sendEmail);
//
//        JSONObject response = new JSONObject(value);
//        return response.toString();
//    }
//
//    @POST
//    @Path("acceptRequest")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String acceptRequest (long idRequest){
//        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
//        boolean value = manager.acceptRequest(idRequest);
//
//        JSONObject response = new JSONObject(value);
//        return response.toString();
//    }
//
//    @POST
//    @Path("refuseRequest")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String refuseRequest (long idRequest){
//        RequestRelationshipManager manager = RequestRelationshipManagerFactory.create();
//        boolean value = manager.acceptRequest(idRequest);
//
//        JSONObject response = new JSONObject(value);
//        return response.toString();
//    }

}
