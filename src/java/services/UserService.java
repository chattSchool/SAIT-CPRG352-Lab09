package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author Dakota Chatt
 * @version June 25, 2022
 */
public class UserService {
    
    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    public User getUser(String userEmail) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(userEmail);
        return user;
    }
    
    public void addUser(String email, boolean active, String firstName, String lastName, String password, Role role) throws Exception {
        UserDB userDB = new UserDB();
        User user = new User(email, active, firstName, lastName, password);
        user.setRole(role);
        userDB.addUser(user);
    }
    
    public void updateUser(String email, boolean active, String firstName, String lastName, String password, Role role) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        Role prevRole = user.getRole(); //Grabs the users current role before the update, to maintain bi-directionality
        user.setActive(active);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(role);
        userDB.updateUser(user, prevRole);
    }
    
    public void deleteUser(String userEmail) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(userEmail);
        userDB.deleteUser(user);
    }
}
