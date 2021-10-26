/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;

/**
 *
 * @author BritishWaldo
 */
public class RoleDB
{
    public ArrayList<Role> getAll() throws Exception 
    {
        ArrayList<Role> allRoles = new ArrayList<Role>();
        
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        ResultSet sqlResults = null;
        
        String sqlStatement = "SELECT role_id, role_name "
                                + "from role";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlResults = sqlPreparedStatement.executeQuery();
            while (sqlResults.next()) 
            {
                int roleID = sqlResults.getInt(1);
                String originalRoleName = sqlResults.getString(2);
                String[] splitRoleName = originalRoleName.split(" ");
                
                String roleName = "";
                
                for(String partialRoleName: splitRoleName)
                {
                    roleName += partialRoleName.substring(0, 1).toUpperCase() + partialRoleName.substring(1) + " ";
                }
                
                roleName = roleName.trim();

                Role tempRole = new Role(roleID, roleName);
                allRoles.add(tempRole);
            }
        } 
        finally 
        {
            DBUtil.closeResultSet(sqlResults);
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }

        return allRoles;
    }

    public Role get(int inputRoleID) throws Exception 
    {
        Role tempRole = null;
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        ResultSet sqlResults = null;
        
        String sqlStatement = "SELECT * "
                                + "from role "
                                + "where role_id = ?";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlPreparedStatement.setString(1, "" + inputRoleID);
            sqlResults = sqlPreparedStatement.executeQuery();
            while (sqlResults.next()) 
            {
                int roleID = sqlResults.getInt(1);
                String originalRoleName = sqlResults.getString(2);
                String[] splitRoleName = originalRoleName.split(" ");
                
                String roleName = "";
                
                for(String partialRoleName: splitRoleName)
                {
                    roleName += partialRoleName.substring(0, 1).toUpperCase() + partialRoleName.substring(1) + " ";
                }
                
                roleName = roleName.trim();
                tempRole = new Role(roleID, roleName);
            }
        } 
        finally 
        {
            DBUtil.closeResultSet(sqlResults);
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }
        
        return tempRole;
    }

    public void insert(Role inputRole) throws Exception 
    {
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        
        String sqlStatement = "INSERT INTO role "
                                + "(role_id, role_name) "
                                + "VALUES (?, ?)";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlPreparedStatement.setString(1, "" + inputRole.getRoleID());
            sqlPreparedStatement.setString(2, inputRole.getRoleName());
            sqlPreparedStatement.executeUpdate();
        } 
        finally
        {
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }
    }

    public void update(Role inputRole) throws Exception 
    {
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        
        String sqlStatement = "UPDATE role "
                                + "SET role_name = ? "
                                + "where role_id = ?";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlPreparedStatement.setString(1, inputRole.getRoleName());
            sqlPreparedStatement.setString(2, "" + inputRole.getRoleID());
            sqlPreparedStatement.executeUpdate();
        } 
        finally
        {
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }
    }

    public void delete(Role inputRole) throws Exception 
    {        
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        
        String sqlStatement = "delete "
                                + "from role "
                                + "where role_id = ?";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlPreparedStatement.setString(1, "" + inputRole.getRoleID());
            sqlPreparedStatement.executeUpdate();
        } 
        finally
        {
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }
    }
}
