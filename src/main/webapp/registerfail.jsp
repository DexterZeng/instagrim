<%-- 
    Document   : registerfail
    Created on : Nov 4, 2015, 3:17:01 AM
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
                color:orange;
                font-style:oblique;
                font-weight:normal;
                font-size:80%;
            }
            strong{
                color:salmon;
                font-variant:small-caps;
                font-size:100%;
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
            <h3>Register as user</h3>
            <form method="POST"  action="Register">
                <p><strong>Sorry, the username is already Occupied</strong></p>
                <p>The User Name and Password are necessary,<br>
                   The others are optional
                </p>
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>Password <input type="password" name="password"></li>
                    <li>first_name <input type="text" name="first_name"></li>
                    <li>last_name <input type="text" name="last_name"></li>
                    <li>Email <input type="email" name="email"></li>
                    
					
				
                </ul>
                <br/>
                <input type="submit" value="Register">
            </form>
        </article>
    </body>
</html>
