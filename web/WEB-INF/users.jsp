<%-- 
    Document   : users
    Created on : 25-Jun-2022, 4:41:23 PM
    Author     : Dakota Chatt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="./styles/users.css">

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>User Admin Portal</h1>
        
        <%-- Displays all users currently in the database --%>
        <div class="userTableContainer">
            <h2>Manage Users</h2>
            <table class="userTable">
                <tr>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>Email</th>
                    <th>User Role</th>
                    <th>Active?</th>
                    <th>Update User</th>
                    <th>Delete User</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.lastName}</td>
                        <td>${user.firstName}</td>
                        <td>${user.email}</td>
                        <td>${user.role.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.active == false}">
                                    No
                                </c:when>
                                <c:otherwise>
                                    Yes
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <%-- Gets the user's email for the particular row being edited --%>
                            <c:url value="/users" var="updateURL">
                                <c:param name="action" value="update" />
                                <c:param name="userEmail" value="${user.email}" />
                            </c:url>

                            <a href="${updateURL}">Update</a>                        
                        </td>
                        <td>
                            <%-- Gets the user's email for the particular row being deleted --%>
                            <c:url value="/users" var="deleteURL">
                                <c:param name="action" value="delete" />
                                <c:param name="userEmail" value="${user.email}" />
                            </c:url>

                            <a href="${deleteURL}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        
        <%-- Form to add new users to database, hides when a user is being updated --%>
        <c:if test="${user == null}">
            <form class="addUserForm" method="post" action="">
                <h2>Add User</h2>
                <input type="hidden" name="action" value="add">
                <input class="textField formInput" type="text" name="new_email" value="" placeholder="Email Address" required />
                <br />
                <input class="textField formInput" type="text" name="new_fname" value="" placeholder="First Name" required />
                <br />
                <input class="textField formInput" type="text" name ="new_lname" value="" placeholder="Last Name" required />
                <br />
                <input class="textField formInput" type="password" name="new_password" value="" placeholder="Password" required />
                <br />
                <select class="formInput"  name="new_roles">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.id}">${role.name}</option>
                    </c:forEach>
                </select>
                <label>Active?</label>
                <input class="formInput" type="checkbox" name="new_active" />
                <br />
                <input class="submitButton actionButton formInput" type="submit" value="Add User">
                <br />
            </form>
        </c:if>

        
        <%-- Only shows the edit user form if a user has been selected to be updated --%>
        <c:if test="${user != null}">
            <form class="updateUserForm" action="" method="post">
                <h2>Update User</h2>
                <input class="textField formInput" type="text" name="update_email" value="${user.email}" placeholder="Email Address" disabled required />
                <br />
                <input class="textField formInput" type="text" name="update_fname" value="${user.firstName}" placeholder="First Name" required />
                <br />
                <input class="textField formInput" type="text" name ="update_lname" value="${user.lastName}" placeholder="Last Name" required />
                <br />
                <input class="textField formInput" type="password" name="update_password" value="${user.password}" placeholder="Password" required />
                <br />
                <select class="formInput" name="update_roles">
                    <c:forEach var="role" items="${roles}">
                        <c:choose>
                           <c:when test="${role.id == user.role.id}">
                               <option value="${role.id}" selected>${role.name}</option>
                           </c:when>
                           <c:otherwise>
                               <option value="${role.id}">${role.name}</option>
                           </c:otherwise>
                       </c:choose>
                    </c:forEach>
                </select>
                <label for="update_active">Active?</label>
                <c:choose>
                    <c:when test="${user.active == true}">
                        <input id="update_active" class="formInput" type="checkbox" name="update_active" checked="checked" />
                    </c:when>
                    <c:otherwise>
                        <input id="update_active" class="formInput" type="checkbox" name="update_active" />
                    </c:otherwise>
                </c:choose>        
                <br />
                <input class="submitButton actionButton formInput" type="submit"  name="action" value="Update">
                <br />
                <input class="submitButton cancelButton formInput" type="submit" name="action" value="Cancel">
            </form>            
        </c:if>

            
        <c:if test="${message != null}">
            <p>${message}</p>
        </c:if>
    </body>
</html>
