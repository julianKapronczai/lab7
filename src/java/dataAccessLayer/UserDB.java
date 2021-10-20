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
import models.User;

/**
 *
 * @author BritishWaldo
 */
public class UserDB
{
    public ArrayList<User> getAll() throws Exception 
    {
        ArrayList<User> allUsers = new ArrayList<User>();
        
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        ResultSet sqlResults = null;
        
        String sqlStatement = "SELECT * "
                                + "from user";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlResults = sqlPreparedStatement.executeQuery();
            while (sqlResults.next()) 
            {
                String userEmail = sqlResults.getString(1);
                int userActive = sqlResults.getInt(2);
                String userFirstName = sqlResults.getString(3);
                String userLastName = sqlResults.getString(4);
                String userPassword = sqlResults.getString(5);
                int userRole = sqlResults.getInt(6);
                User tempUser = new User(userEmail, userActive, userFirstName, userLastName, userPassword, userRole);
                allUsers.add(tempUser);
            }
        } 
        finally 
        {
            DBUtil.closeResultSet(sqlResults);
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }

        return allUsers;
    }

    public User get(String inputUserEmail) throws Exception 
    {
        User tempUser = null;
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        ResultSet sqlResults = null;
        
        String sqlStatement = "SELECT * from user "
                                + "where email = ?";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlPreparedStatement.setString(1, "" + inputUserEmail);
            sqlResults = sqlPreparedStatement.executeQuery();
            while (sqlResults.next()) 
            {
                String userEmail = sqlResults.getString(1);
                int userActive = sqlResults.getInt(2);
                String userFirstName = sqlResults.getString(3);
                String userLastName = sqlResults.getString(4);
                String userPassword = sqlResults.getString(5);
                int userRole = sqlResults.getInt(6);
                tempUser = new User(userEmail, userActive, userFirstName, userLastName, userPassword, userRole);
            }
        } 
        finally 
        {
            DBUtil.closeResultSet(sqlResults);
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }
        
        return tempUser;
    }

    public void insert(User inputUser) throws Exception 
    {
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        
        String sqlStatement = "INSERT INTO user "
                                + "(email, active, first_name, last_name, password, role)"
                                + " VALUES (?, ?, ?, ?, ?, ?)";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlPreparedStatement.setString(1, "" + inputUser.getEmail());
            
            if (inputUser.isActive())
            {
                sqlPreparedStatement.setString(2, "" + 1);
            }
            else
            {
                sqlPreparedStatement.setString(2, "" + 0);
            }
            
            sqlPreparedStatement.setString(3, "" + inputUser.getFirstName());
            sqlPreparedStatement.setString(4, "" + inputUser.getLastName());
            sqlPreparedStatement.setString(5, "" + inputUser.getPassword());
            sqlPreparedStatement.setString(6, "" + inputUser.getUserRole());
            sqlPreparedStatement.executeUpdate();
        } 
        finally
        {
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }
    }

    public void update(User inputUser) throws Exception 
    {
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        
        String sqlStatement = "UPDATE user SET email = ?, "
                                + "active = ?, "
                                + "first_name = ?, "
                                + "last_name = ?, "
                                + "password = ?, "
                                + "role = ? "
                                + "where email = ?";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlPreparedStatement.setString(1, "" + inputUser.getEmail());
            
            if (inputUser.isActive())
            {
                sqlPreparedStatement.setString(2, "" + 1);
            }
            else
            {
                sqlPreparedStatement.setString(2, "" + 0);
            }
            
            sqlPreparedStatement.setString(3, "" + inputUser.getFirstName());
            sqlPreparedStatement.setString(4, "" + inputUser.getLastName());
            sqlPreparedStatement.setString(5, "" + inputUser.getPassword());
            sqlPreparedStatement.setString(6, "" + inputUser.getUserRole());
            sqlPreparedStatement.executeUpdate();
        } 
        finally
        {
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }
    }

    public void delete(User inputUser) throws Exception 
    {        
        ConnectionPool dBConnectionPool = ConnectionPool.getInstance();
        Connection dbConnection = dBConnectionPool.getConnection();
        
        PreparedStatement sqlPreparedStatement = null;
        
        String sqlStatement = "delete from user "
                                + "where email = ?";
        
        try 
        {
            sqlPreparedStatement = dbConnection.prepareStatement(sqlStatement);
            sqlPreparedStatement.setString(1, "" + inputUser.getEmail());
            sqlPreparedStatement.executeUpdate();
        } 
        finally
        {
            DBUtil.closePreparedStatement(sqlPreparedStatement);
            dBConnectionPool.freeConnection(dbConnection);
        }
    }
}
