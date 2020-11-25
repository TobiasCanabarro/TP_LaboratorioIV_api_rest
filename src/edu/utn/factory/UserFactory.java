package edu.utn.factory;

import edu.utn.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;

public class UserFactory implements Factory {


    //Este metodo se usa cuando se registra un usuario
    public static User create(JSONObject object){
        return new User(object.get("name").toString(), object.get("password").toString(), object.getString("surname").toString(),
                object.getString("email").toString(), object.getString("nickname").toString(), Date.valueOf(object.getString("birthday")));
    }


    //Este metodo se usa para crear el usuario que tiene los cambios
    public static User create (JSONObject jsonObject, User user) {

        Date birthday = Date.valueOf(jsonObject.getString("birthday"));
        User userWithChange = new User(jsonObject.getString("name"),
                user.getPassword(),
                jsonObject.getString("surname"),
                jsonObject.getString("email"),
                jsonObject.getString("nickname"), birthday);

        userWithChange.setAttemptLogin(user.getAttemptLogin() );
        userWithChange.setLocked(user.isLocked());
        userWithChange.setLogIn(user.isLogIn());

        return userWithChange;
    }


    //Este metodo se usa cuando se hace un getAll de los usuarios
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
