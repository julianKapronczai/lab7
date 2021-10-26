/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataAccessLayer.RoleDB;
import java.util.ArrayList;
import models.Role;



/**
 *
 * @author BritishWaldo
 */
public class RoleService
{
    public Role get(int inputRoleID) throws Exception 
    {
        RoleDB roleConnection = new RoleDB();
        Role tempRole = roleConnection.get(inputRoleID);
        return tempRole;
    }
    
    public ArrayList<Role> getAll() throws Exception 
    {
        RoleDB roleConnection = new RoleDB();
        ArrayList<Role> allRolesList = roleConnection.getAll();
        return allRolesList;
    }
    
    public void insert(int inputRoleID, String inputRoleName) throws Exception 
    {
        RoleDB roleConnection = new RoleDB();
        Role tempRole = new Role(inputRoleID, inputRoleName);
        roleConnection.insert(tempRole);
    }
    
    public void update(int inputRoleID, String inputRoleName) throws Exception 
    {
        RoleDB roleConnection = new RoleDB();
        Role tempRole = new Role(inputRoleID, inputRoleName);
        roleConnection.update(tempRole);
    }
    
    public void delete(int inputRoleID) throws Exception 
    {
        RoleDB roleConnection = new RoleDB();
        Role tempRole = new Role(inputRoleID);
        roleConnection.update(tempRole);
    }
    
    public int roleIDLookup(String lookupString)
    {
        int tempRoleID = -1;
        
        switch (lookupString)
        {
            case "System Administrator":    tempRoleID = 1;
                                            break;
            case "Regular User":            tempRoleID = 2;
                                            break;
            case "Company Administrator":   tempRoleID = 3;
                                            break;
        }
        
        return tempRoleID;
    }
}
