<%-- 
    Document   : index1
    Created on : 2015-10-21, 4:16:32
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
                }
                #Pic{
                position:fixed;     
                top: 200px;
                left:300px;                       
                }
                #Word{
                position:fixed;
                top:220px;
                right:550px;
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
                <li><a href="/instagrim/upload.jsp">Upload</a></li>
                <li><a href="/instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="/instagrim/LogOut">LogOut</a></li>
	        <li><a href="/instagrim/UserInfo.jsp">Your Info</a></li>
                <li><a href="/instagrim/SearchOth.jsp">Other Users</a></li>
                 
                
                
                    <%}
                            }else{
                                %>
                 <li><a href="register.jsp">Register</a></li>
                <li><a href="login.jsp">Login</a></li>
                <% }%>
            </ul>
        </nav>
            
        <article id="Pic">
         <% if (lg!=null && lg.getlogedin()){
    	   
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics1");
            if (lsPics == null) {
         %>
         <p>Haven't uploaded your portrait yet! </p>
         <p><a href="/instagrim/Profile.jsp?id='upload'">Upload</a></p>
        
        <%
        } else {
        
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
         
                Pic p = (Pic) iterator.next();
                if(p.getSUUID()!=null){
                	%>
                	<a href="/instagrim/Profile.jsp?id='change'" ><img src="/instagrim/Thumb1/<%=p.getSUUID()%>"></a><br/>
 
                    <%     
                            }                
                        }
         }
                    %>
        </article>
         <article id="Word">
         <%if (lg != null) {%>       	
         <p> Welcome <%=lg.getfirst_name() %> <%=lg.getlast_name() %>  ! </p>
         <h6> Click Profile Picture To Change it</h6>
         </article>     
         <%}%>         
    </body>
</html>
