<%-- 
    Document   : UserInfo
    Created on : 2015-10-21, 23:51:01
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css"  />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            h2 {background-color:black;
                    color: white;
                    font-variant: small-caps;
                    display:block;
                    font-weight:bold;
                    width:400px;
                    text-align:center;
                    padding:4px;
                    text-decoration:none;  
                    }
            h1 {font-size: 300%;
                    font-style: italic;
                    font-variant: small-caps;}
            body
                {
                    background-image: url(http://www.technosamrat.com/wp-content/uploads/2012/05/Black-and-White-Wallpaper-Picture.jpg);
                    background-position: center;
                    background-repeat: repeat-y;
                    background-position: 0px -50px;
                } 
            nav a:link,nav a:visited
                {
                display:block;
                font-weight:bold;
                color:white;
                background-color:#bebebe;
                width:120px;
                text-align:center;
                padding:4px;
                text-decoration:none;
                text-transform:uppercase;
                }
            nav a:hover,nav a:active
                {
                background-color:black;
                }
            ul{
                list-style-type:none;
                margin:0;
                padding:0;
                }
            article{
                font-style: italic;
                font-weight: bold;
                color:black;
                text-align: left;
                position:relative;
                top:0px;
                left:380px;
                }
            a:link,a:visited
                {
                display:block;
                font-weight:bold;
                color:white;
                background-color:#bebebe;
                width:120px;
                text-align:center;
                padding:4px;
                text-decoration:none;
                text-transform:uppercase;
                }
                a:hover,a:active
                {
                background-color:black;
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
                        if (lg != null) {
                           
                            
                            if (lg.getlogedin()) {
                    %>
                    <li><a href="TransToInd.jsp">Home</a></li>                 
                    <%}
                            }%>
            </ul>
        </nav>        
        <article>
         <%if (lg != null) {%>       	
         <p> Your Username <%=lg.getUsername() %></p>
         <p> Your Firstname <%=lg.getfirst_name() %></p> 
         <p> Your Lastname  <%=lg.getlast_name() %> </p>
         <p> Your Email <%=lg.getemail() %></p>
         
         <ul>
         <li><a href="ChangeInfo.jsp">Change Info</a></li>
         </ul>
         </article>     
         <%}%>     
        
        
    </body>
</html>
