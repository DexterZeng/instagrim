<%-- 
    Document   : friends
    Created on : Nov 4, 2015, 4:51:45 AM
    Author     : lenovo
--%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>friends</title>
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
                <li class="nav"><a href="/instagrim/TransToInd.jsp">Home</a></li>
            </ul>
    </nav>
    
        <article>
            
          <%  Set<String> friends= (Set<String>) request.getAttribute("friends");
            if (friends == null) {
        %>
        <p>No Friends Found</p>
        <%
        } else {
            Iterator<String> iterator;
            iterator = friends.iterator();
           
            while (iterator.hasNext()) {     
          
               String friend= (String) iterator.next();

        %>
        <p> <%= friend %> </p>
        <br>
       <%} 
       }%> 
       
</article>       
</body>
</html>
