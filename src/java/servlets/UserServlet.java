package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import services.*;

/**
 *
 * @author Dakota Chatt
 * @version June 25, 2022
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UserService us = new UserService();
        RoleService rs = new RoleService();
        String action = request.getParameter("action");
        
        try {
           //Gets all users
           ArrayList<User> users = us.getAll();
           request.setAttribute("users", users);
         
           //Gets all role names
           ArrayList<Role> roles = rs.getAll();
           request.setAttribute("roles", roles);
           
           if(action != null) {
                if(action.equals("delete")) {
                    String userEmail = request.getParameter("userEmail");
                    us.deleteUser(userEmail);
                    users = us.getAll();
                    request.setAttribute("users", users);
                    
                    response.sendRedirect("users");
                    session.setAttribute("message", null);
                    return;
                } else if (action.equals("update")) { //Clicking the update link fills the update form, actual changes will be done in doPost
                    String userEmail = request.getParameter("userEmail");
                    User user = us.getUser(userEmail);
                    request.setAttribute("user", user);
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong, please try again. Error: " + e);
            session.setAttribute("message", e);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        session.setAttribute("message", null);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UserService us = new UserService();
        RoleService rs = new RoleService();
        String action = request.getParameter("action");
        request.setAttribute("action", action);
        
        try {
            if(action != null) {
                switch(action.toLowerCase()) {
                    case "add":
                        boolean active = false;
                        if(request.getParameter("new_active") != null) {
                            active = true;
                        }
                        String email = request.getParameter("new_email");
                        String firstName = request.getParameter("new_fname");
                        String lastName = request.getParameter("new_lname");
                        String password = request.getParameter("new_password");
                        Role role = rs.getRole(Integer.parseInt(request.getParameter("new_roles")));
                        System.out.println("Here");
                        us.addUser(email, active, firstName, lastName, password, role);
                        System.out.println("No Here");
                        break;
                    case "update":
                        active = false;
                        if(request.getParameter("update_active") != null) {
                            active = true;
                        }
                        email = request.getParameter("userEmail");
                        firstName = request.getParameter("update_fname");
                        lastName = request.getParameter("update_lname");
                        password = request.getParameter("update_password");
                        role = rs.getRole(Integer.parseInt(request.getParameter("update_roles")));
                        us.updateUser(email, active, firstName, lastName, password, role);
                        break;
                    case "cancel":
                        response.sendRedirect("users");
                        return;
                }
            }
        } catch (Exception e) {
            session.setAttribute("message", "Something went wrong, please try again. Error: " + e);
        }
        
        response.sendRedirect("users");
        return;
    }
}
