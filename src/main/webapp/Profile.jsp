<%-- 
    Document   : Profile
    Created on : 2015-10-21, 4:10:56
    Author     : lenovo
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <style>
            p{
                font-weight: bold;
                font-size: 150%;
                position:fixed;     
                top: 200px;
                left:300px;
                font-style: italic;
                font-variant: small-caps;
            }
        </style>
        <title>Add Profile</title>
    </head>
    <body>
         <header>
            <h1>Instagrim ! </h1>
            <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                <li class="nav"><a href="/instagrim/TransToInd.jsp">Home</a></li>
            </ul>
        </nav>
            
        <% 
            String state=request.getParameter("id");

            if (state=="upload"){	
        %>
        <article>
        <form method="POST" name="myform" enctype="multipart/form-data" action="Picture">
           <p> Upload Your Profile Picture: <input type="file" name="upfile" ><br/>
                <input type="submit" value="Press"> </p>
        </form>
        <%
        }else{
        %>
        <form method="POST" name="myform" enctype="multipart/form-data" action="Picture">
            <p> Change Your Profile Picture: <input type="file" name="upfile" ><br/>
                <input type="submit" value="Press"> </p>
        </form>
        <%
        }
        %>
        </article>
    </body>
</html>
