<%-- 
    Document   : CommentList
    Created on : 2015-10-21, 4:09:53
    Author     : lenovo
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.models.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.*" %>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Comment List</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <style>
            p{
                background-color:black;
                color: white;
                display:block;
                font-weight:bold;
                text-align:center;
                padding:4px;
                text-decoration:none;  
            }
            
        </style>
    </head>
<body>
    <header>
        <h1>instagrim ! </h1>
        <h2>Your world in Black and White</h2>
    </header>

    <nav>
            <ul>
                <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (!lg.getvisiting()) {
                    %>
                <li class="nav"><a href="/instagrim/Images/<%=lg.getUsername()%>">Back To Pic</a></li>
                <li class="nav"><a href="/instagrim/TransToInd.jsp">Home</a></li>
                <%}else{%>
                <li class="nav"><a href="/instagrim/Images/<%=lg.getOtherUsername()%>">Back To Pic</a></li>
                <li class="nav"><a href="/instagrim/TransToInd.jsp">Home</a></li>
            </ul>
    </nav>
    
    
           <%}%>
        <article>
            
          <%  java.util.LinkedList<Com> com = (java.util.LinkedList<Com>) request.getAttribute("Comment");
            if (com == null) {
        %>
        <p>No Comments found</p>
        <%
        } else {
            Iterator<Com> iterator;
            iterator = com.iterator();
           
            while (iterator.hasNext()) {     
          
               Com c = (Com) iterator.next();

        %>
        <p> <%=c.getCom() %> </p>
        <br>
       <%} 
       }%> 
       
</article>       
</body>
</html>