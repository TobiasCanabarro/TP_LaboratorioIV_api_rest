package edu.utn.factory;

import edu.utn.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;

public class UserFactory implements Factory {

    public static User create(JSONObject object){
        return new User(object.get("name").toString(), object.get("password").toString(), object.getString("surname").toString(),
                object.getString("email").toString(), object.getString("nickname").toString(), Date.valueOf(object.getString("birthday")));
    }

    public static JSONArray create (List<User> users) {
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
        return jsonArray;
    }

}
