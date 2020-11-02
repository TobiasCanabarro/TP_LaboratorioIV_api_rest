package edu.utn;

import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;

import java.sql.Date;

public class Main {

    public static void main (String [] args) {
        User user = new User("Hernandez", "hernandez123",
                "Fernandez", "hernandez@gmail.com", "hernan", new Date(9999));
        UserManager manager = UserManagerFactory.create();
        boolean value = manager.signIn(user);
        System.out.println(value);
    }
}
