/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
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
        
        if(request.getParameter("active") == "active"){
            active = true;
        }
        
        if(email == null || email.equals("")){
            request.setAttribute("angy_message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
        }else if (firstName == null || firstName.equals("")){
            request.setAttribute("angy_message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
        }else if (lastName == null || lastName.equals("")){
            request.setAttribute("angy_message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;
            
        }else if (password == null || password.equals("")){
            request.setAttribute("angy_message", "please enter a valid email");
            
            getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
            return;                
        }else{
            
            try {
                new UserService().insert(email, active, firstName, lastName, password, new RoleService().roleIDLookup(userType));
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                session.setAttribute("usersList", new UserService().getAll());
                request.setAttribute("usersList", new UserService().getAll());
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
}
