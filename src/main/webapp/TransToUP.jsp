<%-- 
    Document   : TransToUP
    Created on : 2015-10-21, 4:15:44
    Author     : lenovo
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
        <% LoggedIn lg=(LoggedIn) session.getAttribute("LoggedIn");
           if(!lg.getvisiting()){
        %>
        <meta http-equiv="refresh" content="0;url=/instagrim/Images/<%=lg.getUsername()%>" >
        <%}else{%>
        <meta http-equiv="refresh" content="0;url=/instagrim/Images/<%=lg.getOtherUsername()%>" >
        <%}%>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Instagrim</title>
    </head>
    <body>

    </body>
</html>
