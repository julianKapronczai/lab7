/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author BritishWaldo
 */
public class Role implements Serializable
{
    private int roleID;
    private String roleName;
    
    public Role()
    {
        this.roleID = -1;
        this.roleName = "N/A";
    }
    
    public Role(int inputRoleID, String inputRoleName)
    {
        this.roleID = inputRoleID;
        this.roleName = inputRoleName;
    }

    public Role(int inputRoleID)
    {
        this.roleID = inputRoleID;
        this.roleName = null;
    }

    public int getRoleID()
    {
        return this.roleID;
    }

    public String getRoleName()
    {
        return this.roleName;
    }
    
    public void setRoleID(int inputRoleID)
    {
        this.roleID = inputRoleID;
    }
    
    public void setRoleID(String inputRoleName)
    {
        this.roleName = inputRoleName;
    }
    
    @Override
    public String toString()
    {
        return "Role ID=" + this.getRoleID() + " Role Name=" + this.getRoleName();
    }
}
