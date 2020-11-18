package edu.utn.factory;

import edu.utn.entity.Login;
import org.json.JSONObject;

public class LoginFactory implements Factory {

    public static Login create (JSONObject object){
        return new Login(object.getString("email"), object.getString("password"));
    }

}
