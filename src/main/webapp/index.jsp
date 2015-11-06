<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body
            {
                background-image: url(https://newevolutiondesigns.com/images/freebies/colorful-background-30.jpg);
                background-position: center;
                background-repeat: no-repeat;
                background-position: 0px -50px;
                background-size:100%;
            } 
            p{
                 font-style: italic;
                 font-weight: bold;
                 font-variant: small-caps;
                 font-size: 150%;
                 position: relative;
                 top: 250px;
                 left:450px;
            }
            strong{
                color: white;
            }
            nav {
                text-align: left;
                border: 2px;
                float: left;
                 
            }
            ul{
            list-style-type:none;
            margin:0;
            padding:0;
            position: relative;
            top: 250px;
            left:700px;
            }

            a:link,a:visited
            {
            display:block;
            font-weight:bold;
            color:white;
            background-color:black;
            width:80px;
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
        <p>
            Are <strong>You</strong> Ready <strong>To</strong> Embrace <br>
            <strong>The</strong> World <strong> Of</strong> Black<strong> And White</strong>
        </p>
        <nav>
            <ul>               
                <li><a href="index2.jsp">Next</a></li>

            </ul>
        </nav>        
    </body>
</html>
