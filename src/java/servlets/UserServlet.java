/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.RoleService;
import services.UserService;


/**
 *
 * @author BritishWaldo
 */
public class UserServlet extends HttpServlet
{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        
        HttpSession session = request.getSession();
        
        try {
            ArrayList<User> userList = new UserService().getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ArrayList<User> userList = new UserService().getAll();
                session.setAttribute("usersList", userList);
                request.setAttribute("usersList", userList);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
        return;
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        String email = request.getParameter("addEmail");
        String firstName = request.getParameter("addFName");
        String lastName = request.getParameter("addLName");
        String password = request.getParameter("addPassword");
        String userType = request.getParameter("addUserType");
        Boolean active = false;

        if(request.getParameter("action").equals("edit")){
            request.setAttribute("message", "Edit peen pressed bitch");
            
            String ogEmail = request.getParameter("selected");
            UserService editUser = new UserService(); 
            User tempUser = new User();
            
            try {
                tempUser = editUser.get(ogEmail);
                
                request.setAttribute("editEmail", tempUser.getEmail());
                request.setAttribute("editFName", tempUser.getFirstName());
                request.setAttribute("editLName", tempUser.getLastName());
                request.setAttribute("editPassword", tempUser.getPassword());
                request.setAttribute("ogEmail", ogEmail);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
        }else if (request.getParameter("action").equals("delete")){
            request.setAttribute("message", "delete peen pressed bitch");
            try {
            String ogEmail = request.getParameter("selected");
            UserService editUser = new UserService(); 
            User tempUser = new User(); 
            tempUser = editUser.get(ogEmail);
            
            if(request.getParameter("selected") == null){
                request.setAttribute("message", "please enter a valid email");
            
                getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
             return;
            }else{
                
                new UserService().delete(ogEmail);
                getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
             return;
            }
            
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
        }else if (request.getParameter("action").equals("add")){
            if(request.getParameter("active") == "active"){
            active = true;
        }
        
        if(email == null || email.equals("")){
            request.setAttribute("message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
        }else if (firstName == null || firstName.equals("")){
            request.setAttribute("message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
        }else if (lastName == null || lastName.equals("")){
            request.setAttribute("message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
            
        }else if (password == null || password.equals("")){
            request.setAttribute("message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;                
        }else{
            
            try {
                new UserService().insert(email, active, firstName, lastName, password, new RoleService().roleIDLookup(userType));
                try {
                    
            ArrayList<User> userList = new UserService().getAll();
                session.setAttribute("usersList", userList);
                request.setAttribute("usersList", userList);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  
        }
        else if (request.getParameter("action").equals("updateUser")){
            try{
                
            String ogEmail = request.getParameter("selected");
            UserService editUser = new UserService(); 
            User tempUser = new User(); 
            tempUser = editUser.get(ogEmail);            
                
        if(request.getParameter("editEmail") == null){
            request.setAttribute("message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
        }if (request.getParameter("editFName")== null){
            request.setAttribute("message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
        }if (request.getParameter("editLName")== null){
            request.setAttribute("message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
            
        }if (request.getParameter("editPassword")== null){
            request.setAttribute("message", "please enter a valid email");

            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;                
        }else{
            
            try {
                
                boolean editActive = false;
                if(request.getParameter("active") == "active"){
                    editActive = true;}
                new UserService().update(ogEmail, request.getParameter("editEmail"),editActive,request.getParameter("editFName") , request.getParameter("editLName"), new RoleService().roleIDLookup(request.getParameter("editUserType")));
                
                ArrayList<User> userList = new UserService().getAll();
                session.setAttribute("usersList", userList);
                request.setAttribute("usersList", userList);
  
                getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
                    return;  
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            }catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
}}}
