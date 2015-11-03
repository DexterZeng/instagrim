<%-- 
    Document   : loginfail
    Created on : Oct 26, 2015, 6:04:42 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <style>
            p{
                color:tomato;
                font-style: italic;
                font-variant: small-caps;
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
                <li><a href="index2.jsp">Home</a></li>
            </ul>
        </nav>
       
        <article>
            <h3>Login</h3>
            <p>Sorry, the password is not corresponding to the username
                <br> Or you have not registered as a user
            </p>
            <p>Please Try Again :)</p>
            <form method="POST"  action="Login">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>Password <input type="password" name="password"></li>

                </ul>
                <br/>
                <input type="submit" value="Login"> 
            </form>

        </article>
    </body>
</html>
