/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataAccessLayer.UserDB;
import java.util.ArrayList;
import models.User;

/**
 *
 * @author BritishWaldo
 */
public class UserService
{
    public User get(String inputEmail) throws Exception 
    {
        UserDB userConnection = new UserDB();
        User tempUser = userConnection.get(inputEmail);
        return tempUser;
    }
    
    public ArrayList<User> getAll() throws Exception 
    {
        UserDB userConnection = new UserDB();
        ArrayList<User> allUsersList = userConnection.getAll();
        return allUsersList;
    }
    
    public void insert(String inputEmail, boolean inputActive, String inputFirstName, String inputLastName
                    , String inputPassword, int inputUserRole) throws Exception 
    {
        UserDB userConnection = new UserDB();
        User tempUser = new User();
        userConnection.insert(tempUser);
    }
    
    public void update(String inputEmail, boolean inputActive, String inputFirstName, String inputLastName
                    , String inputPassword, int inputUserRole) throws Exception 
    {
        UserDB userConnection = new UserDB();
        User tempUser = new User();
        userConnection.update(tempUser);
    }
    
    public void delete(String inputEmail) throws Exception 
    {
        UserDB userConnection = new UserDB();
        User tempUser = new User(inputEmail);
        userConnection.update(tempUser);
    }
}
