<%-- 
    Document   : users
    Created on : 20-Oct-2021, 10:29:44
    Author     : BritishWaldo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Huge Penis User Login, Edit, ADD page Fuck yea</title>
    </head>
    <body>
        <h1>Welcome</h1>
        <label for="addUserForm">Add Users</label>
            <form action="" method="post" id="addUserForm">
                    <input type="text" name="addEmail" value="Email" id="addEmail"><br>
                    <input type="text" name="addFName" value="First Name" id="addFName"><br>
                    <input type="text" name="addLName" value="Last Name" id="addLName"><br>
                    <select name="active" id="active">
                        <option value="active" name="active">Active</option>
                        <option value="inactive" name="inactive">Inactive</option>
                    </select><br>
                    <input type="text" name="addPassword" value="Password" id="addPassword"><br>
                    <select name="addUserType" id="addUserType">
                        <option value="System Administrator">System Administrator</option>
                        <option value="Regular User">Regular User</option>
                        <option value="Company Administrator">Company Administrator</option>
                        <option value="Alex Kapronczai">Alex Kapronczai</option>
                        <option value="Julian Hill">Julian Hill</option>
                    </select>
                    <input type="submit" value="addUser"> 
            </form>
            <label for="manageUsers"> Manage Users</label>
            <c:if test="${usersList.size() != null}" >
            <form action="" method="post" id="manageUsers">

                <c:forEach items="${usersList}" var="tempUsers">
                    <c:out value = "
                        <li>
                        <input type='radio' name='userListDisplayed' value='${tempUsers}'>
                        <label for='userListDisplayed'>${tempUsers}</label>
                        </li>
                        " escapeXml="false">
                    </c:out>
                </c:forEach>

            </form>
            <label for="editUserForm">Edit Users</label>
            <form action="" method="post" id="editUserForm">
                <input type="text" name="" value="${usersList}" id="editEmail"><br>
                <input type="text" name="" value="${usersList}" id="editFName"><br>
                <input type="text" name="" value="${usersList}" id="editLName"><br>
                <input type="text" name="" value="${usersList}" id="editPassword"><br>
                <label for="editUserType">${usersList}</label>
                <select name="User Type" id="editUserType">
                    <option value="Sys Admin"></option>
                    <option value="User"></option>
                    <option value="CEO"></option>
                    <option value="Alex Kapronczai"></option>
                    <option value="Julian Hill"></option>
                </select>
                <input type="submit" value="editUser"> 
            </form>
            </c:if>
    </body>
</html>
