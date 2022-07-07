package services;

import dataaccess.UserDB;
import java.util.ArrayList;
import models.Role;
import models.User;

/**
 *
 * @author Dakota Chatt
 * @version June 25, 2022
 */
public class UserService {
    
    public ArrayList<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        ArrayList<User> users = userDB.getAll();
        
        return users;
    }
    
    public User getUser(String userEmail) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(userEmail);
        
        return user;
    }
    
    public void addUser(String email, boolean active, String firstName, String lastName, String password, Role role) throws Exception {
        UserDB userDB = new UserDB();
        User user = new User(email, active, firstName, lastName, password, role);
        userDB.addUser(user);
    }
    
    public void updateUser(String email, boolean active, String firstName, String lastName, String password, Role role) throws Exception {
        UserDB userDB = new UserDB();
        User user = new User(email, active, firstName, lastName, password, role);
        userDB.updateUser(user);
    }
    
    public void deleteUser(String userEmail) throws Exception {
        UserDB userDB = new UserDB();
        User user = new User();
        user.setEmail(userEmail);
        userDB.deleteUser(user);
    }
}
