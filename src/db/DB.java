package db;

import service.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DB{
    private static List<User> users = new ArrayList<User>(){{
        add(new User(1, "test", "test", false));
        add(new User(2, "test1", "test1", false));
    }};

    public static Boolean checkUser(String log, String pass){
        for(User user: users){
            if(user.getNickname().equals(log)){
                if(user.getPassword().equals(pass)) return true;
            }
        }
        return false;
    }

    public User findUser(String log, String pass){
        for(User user: users){
            if(user.getNickname().equals(log)){
                if(user.getPassword().equals(pass)) return user;
            }
        }
        return null;
    }


}
