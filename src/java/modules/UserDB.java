package src.java.modules;

import src.java.objects.User;

import java.util.List;
import java.util.ArrayList;

public class UserDB {
    private List<User> users;

    public UserDB() {
        users = new ArrayList<User>();
    }

    public int addUser(User user) {
        if (checkUser(user) != -1) return -1;

        System.out.println("Added: " + user.toString());
        users.add(user);
        return 0;
    }

    public int checkUser(User user) {
        for (User userIterator : users) {
            if (userIterator.getUsername().equals(user.getUsername())) {
                if (userIterator.getPassword().equals(user.getPassword())) {
                    return 0;
                } else {
                    return -2;
                }
            }
        }

        return -1;
    }
}