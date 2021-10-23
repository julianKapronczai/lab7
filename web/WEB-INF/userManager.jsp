<%-- 
    Document   : users
    Created on : 20-Oct-2021, 10:29:44
    Author     : BritishWaldo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <title>User Managment</title>
</head>

<body>
    <h1>User Managment</h1>

    <table id='outerTable'>
        <tr>    
            <th>Add User</th>   
            <th>Current Users</th>  
            <th>Edit User</th> 
        </tr>
        <tr>    
            <td>
                <table id='newTable'>
                    <tr>
                        <td>
                            <input type="text" name="newEmail" id="newEmail" placeholder="E-mail Address">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="newFirstName" id="newFirstName" placeholder="First Name">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="newLastName" id="newLastName" placeholder="Last Name">
                        </td>
                    </tr>                         
                    <tr>
                        <td>
                            <input type="text" name="newPassword" id="newPassword" placeholder="Password">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select name="newStatus" id="newStatus">
                                <option value="Active">Active</option>
                                <option value="Inactive">Inactive</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select name="newRole" id="newRole">
                                <option value="Standard User">Standard User</option>
                                <option value="Company Administrator">Company Administrator</option>
                                <option value="System Administrator">System Administrator</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
                <table id='displayTable'>
                    <tr>
                        <th>Active status</th>
                        <th>E-mail Address</th>
                        <th>First Name</th>
                        
                        <th>Last Name</th>
                        <th>User Role</th>
                        <th>Edit User</th>
                        <th>Delete user</th>
                    </tr>
                    <c:forEach items="${userList}" var="user"  varStatus="userCount">
                        <c:out value=   " 
                                            <tr>
                                                <form action='' method='post'>    
                                                    <td>
                                                        <c:if test='${user.active = true}'> 
                                                            <span class='material-icons'>check</span>
                                                        </c:if>
                                                        <c:if test='${user.active = false}'> 
                                                            <span class='material-icons'>clear</span>
                                                        </c:if>
                                                    </td>
                                                    <td>${user.email}</td>
                                                    <td>${user.firstName}</td>
                                                    <td>${user.lastName}</td>
                                                    <td>${user.userRole.roleName}</td>
                                                    <td>
                                                        <input type='button' name='edit' id='edit' class='material-icons' value='edit'>
                                                    </td>
                                                    <td>
                                                        <input type='button' name='delete' id='delete' class='material-icons' value='delete'>
                                                    </td>
                                                    <input type='hidden' name='selectUser' value='${user.getEmail()}'>
                                                </form>
                                            </tr>
                                        "
                                escapeXml = "false">
                            </c:out>
                        </c:forEach>
                </table>
            </td>
            <td>
                <table id='editTable'>
                    <tr>
                        <td>
                            <input type="text" name="editEmail" id="editEmail" value="${editEmail}" placeholder="E-mail Address">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="editFirstName" id="editFirstName" value="${editFirstName}" placeholder="First Name">
                        </td> 
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="editLastName" id="editLastName" value="${editLastName}" placeholder="Last Name">
                        </td>
                    </tr>                         
                    <tr>
                        <td>
                            <select name="editStatus" id="editStatus">
                                <option value="Active" <c:if test="${user.isActive}"> selected </c:if>>Active</option>
                                <option value="Inactive" <c:if test="${!user.isActive}"> selected </c:if>>Inactive</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select name="editRole" id="editRole">
                                <option value="Standard User" <c:if test="${user.getRole.getRoleID == 2}"> selected </c:if>>Standard User</option>          
                                <option value="Company Administrator" <c:if test="${user.getRole.getRoleID == 3}"> selected </c:if>>Company Administrator</option>
                                <option value="System Administrator" <c:if test="${user.getRole.getRoleID == 1}"> selected </c:if>>System Administrator</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</body>

</html>