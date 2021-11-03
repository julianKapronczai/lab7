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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
        <title>Windows ME User Manager</title>
    </head>
    <body>
        <h1>Welcome</h1>
        <h2>${message}</h2>
        <div class="container" >
        <label for="addUserForm">Add Users</label>
            <form action="" method="post" id="addUserForm">
                    <input type="text" name="addEmail" placeholder="Email" id="addEmail"><br>
                    <input type="text" name="addFName" placeholder="First Name" id="addFName"><br>
                    <input type="text" name="addLName" placeholder="Last Name" id="addLName"><br>
                    <select name="active" id="active">
                        <option value="active" name="active">Active</option>
                        <option value="inactive" name="inactive">Inactive</option>
                    </select><br>
                    <input type="text" name="addPassword" placeholder="Password" id="addPassword"><br>
                    <select name="addUserType" id="addUserType">
                        <option value="System Administrator">System Administrator</option>
                        <option value="Regular User">Regular User</option>
                        <option value="Company Administrator">Company Administrator</option>
                    </select>
                    <input type="submit" value="addUser">
                    <input type="hidden" name="action" value="add">
            </form>
        </div>
            <div class="container">
            <label for="manageUsers"> Manage Users</label>
            
            
            <table>
                <c:forEach items="${usersList}" var="user">
                    <c:out value = "
                    
                        <tr><td>${user.getEmail()}     ${user.getFirstName()}     ${user.getLastName()}    </td></tr>
                        <tr><td><form action='' method='post'>
                            <input type='submit' value='edit' name='edit'>
                            <input type='hidden' name='selected' value='${user.getEmail()}'>
                            <input type='hidden' name='action' id='action' value='edit'>
                            </form></td></tr>
                        <tr><td><form action='' method='post'>
                            <input type='submit' value='delete' name='delete'>
                            <input type='hidden' name='selected' value='${user.getEmail()}'>
                            <input type='hidden' name='action' id='action' value='delete'>
                            </form></td></tr>
                    " escapeXml = "false" ></c:out>
                </c:forEach>
            </table>
            </div>
            <div class="container">
            <label for="editUserForm">Edit Users</label>
            <form action="" method="post" id="editUserForm">
                <input type="text" name="editEmail" value="${editEmail}" id="editEmail"><br>
                <input type="hidden" name="selected" value="${ogEmail}">
                <input type="text" name="editFName" value="${editFName}" id="editFName"><br>
                <input type="text" name="editLName" value="${editLName}" id="editLName"><br>
                <input type="text" name="editPassword" value="${editPassword}" id="editPassword"><br>
                <label for="editUserType"></label>
                <select name="editUserType" id="editUserType">
                    <option value="System Administrator">System Administrator</option>
                        <option value="Regular User">Regular User</option>
                        <option value="Company Administrator">Company Administrator</option>
                </select>
                <select name="active" id="active">
                        <option value="active" name="active">Active</option>
                        <option value="inactive" name="inactive">Inactive</option>
                    </select><br>
                <input type="submit" value="editUser">
                <input type="hidden" name="action" value="updateUser">
            </form>
        </div>
    </body>
</html>
