/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    Cluster cluster=null;
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String username=request.getParameter("username");
        LoggedIn lg= new LoggedIn();
        lg.setUsername(username);
        
        String password=request.getParameter("password");
        String first_name=request.getParameter("first_name");
        String last_name=request.getParameter("last_name");
        String inputemail=request.getParameter("email");
        Set<String> email=new HashSet<String>();
        email.add(inputemail);
        User us=new User();
        us.setCluster(cluster);
        
        if(!us.checkUsername(username)){
            us.RegisterUser(username, password, first_name, last_name, email);
            response.sendRedirect("index2.jsp");
        }else{
            response.sendRedirect("registerfail.jsp");
        }
        
        
    }  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}