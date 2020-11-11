/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;

/**
 *
 * @author Chels
 */
public class AdminFilter implements Filter {
    
        @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        
        //HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String email = (String)session.getAttribute("email");
        
        UserDB userdb = new UserDB();
        User user = null;
        
        if(email == null)
        {
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.sendRedirect("login");
            return;
        }

            user = userdb.get(email);
            
            Role userRole = user.getRole();
            
            if(userRole.getRoleName()!= "Admin" || userRole.getRoleName() != "admin")
            {
                HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.sendRedirect("notes");
            }
            else
            {
                HttpServletResponse httpResponse = (HttpServletResponse)response;
                httpResponse.sendRedirect("admin");
            }
            chain.doFilter(request, response);
        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
