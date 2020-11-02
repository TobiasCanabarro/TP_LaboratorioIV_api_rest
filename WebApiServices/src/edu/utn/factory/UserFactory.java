package edu.utn.factory;

import edu.utn.entity.User;
import org.json.JSONObject;

import java.sql.Date;

public class UserFactory implements Factory {

    static User create (JSONObject object){
        return new User(object.get("name").toString(), object.get("password").toString(), object.getString("surname").toString(),
                object.getString("email").toString(), object.getString("nickname").toString(), Date.valueOf(object.getString("birthday")));
    }

}
