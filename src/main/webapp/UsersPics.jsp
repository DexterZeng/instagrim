<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instagrim</title>
        <link rel="stylesheet" type="text/css"  />
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
            p{
                color:darksalmon;
            }
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
            article a:link,article a:visited
                {
                
                }
            #Word{
                display:block;
                font-weight:bold;
                color:white;
                background-color:black;
                width:180px;
                text-align:center;
                padding:4px;
                text-decoration:none;
                text-transform:uppercase;  
                }
            article a:hover,article a:active
                {
                background-color:black;
                }
            ul{
                list-style-type:none;
                margin:0;
                padding:0;
                }
            article{
                position: relative;
                top: 0px;
                left:300px;   
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
                    if(!lg.getvisiting()) {%>
                <li class="nav"><a href="/instagrim/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/instagrim/SamplePics.jsp">Sample Images</a></li>
                <li class="nav"><a href="/instagrim/TransToInd.jsp">Home</a></li>
                <%} else{                   
                        %>
                <li class="nav"><a href="/instagrim/TransToInd.jsp">Home</a></li>
                   <% } %>
            </ul>
        </nav>
 
        <article>
            <h1>PICS</h1>
        <%
                      
            if(!lg.getvisiting()){
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
           
            while (iterator.hasNext()) {     
          
               Pic p = (Pic) iterator.next();
            %>
            <a href="/instagrim/Image/<%=p.getSUUID()%>" ><img src="/instagrim/Thumb/<%=p.getSUUID()%>"></a><br/>
            <a id="Word" href="/instagrim/Comment?picid=<%=p.getSUUID()%>">Comment List</a><br/>
             <a id="Word" href="/instagrim/Comment.jsp?picid=<%=p.getSUUID()%>">Add Comment</a><br>
             <a id="Word" href="/instagrim/Delete?picid=<%=p.getSUUID()%>">Delete</a><br/>
           <%
             }}}
            else{
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>The username does not exist or <br>
           The user has not uploaded any picture</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
           
            while (iterator.hasNext()) {     
          
               Pic p = (Pic) iterator.next();

        %>
        <a href="/instagrim/Image/<%=p.getSUUID()%>" ><img src="/instagrim/Thumb/<%=p.getSUUID()%>"></a><br/>
        <a id="Word" href="/instagrim/Comment?picid=<%=p.getSUUID()%>">Comment List</a><br/>
        <a id="Word" href="/instagrim/Comment.jsp?picid=<%=p.getSUUID()%>">Add Comment</a><br>
        
		
                                                
            <% 
            }
            }
            }
        %>
        </article>
    </body>
</html>
