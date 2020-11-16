package edu.utn;

import edu.utn.entity.User;
import edu.utn.factory.UserManagerFactory;
import edu.utn.manager.UserManager;

import java.util.List;

public class Main {

    public static void main (String []args){

        UserManager manager = UserManagerFactory.create();
//        List<User> users = manager.getAllUser();
//
//        for (User user: users) {
//            System.out.println(user.getName());
//        }


    }
}
