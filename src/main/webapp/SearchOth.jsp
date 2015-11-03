<%-- 
    Document   : SearchOth
    Created on : Nov 2, 2015, 9:38:25 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />

    </head>
    <body>
        <header>
        <h1>Instagrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>                
                <li><a href="TransToInd.jsp">Home</a></li>
            </ul>
        </nav>
       
        <article>
            <h3>Search For Other Users</h3>
            <form method="POST"  action="OtherPic">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                </ul>
                <br/>
                <input type="submit" value="Search"> 
            </form>

        </article>
    </body>
</html>
