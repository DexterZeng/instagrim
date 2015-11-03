/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "ChangeInfo", urlPatterns = {"/ChangeInfo"})
public class ChangeInfo extends HttpServlet {
    Cluster cluster=null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session=request.getSession();
         LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
         String username= lg.getUsername();
         String first_name=request.getParameter("first_name");
         String last_name=request.getParameter("last_name");
         String inputemail=request.getParameter("email");
         Set<String> email=new HashSet<String>();
         email.add(inputemail);
         User us=new User();
         us.setCluster(cluster);
         us.ChangeInfo(username,first_name, last_name, email);
         lg.setfirst_name(first_name);
         lg.setlast_name(last_name);
         lg.setemail(email);
	response.sendRedirect("TransToInd.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
