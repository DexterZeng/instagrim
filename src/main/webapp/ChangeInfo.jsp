<%-- 
    Document   : ChangeInfo
    Created on : 2015-10-22, 0:59:07
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
        <h1>instagrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>
                
                <li><a href="TransToInd.jsp">Home</a></li>
            </ul>
        </nav>
       
        <article>
            <h3>Change User Information</h3>
            <form method="POST"  action="ChangeInfo">
                <ul>
                    <li>First Name <input type="text" name="first_name"></li>
                    <li>Last Name <input type="text" name="last_name"></li>
                    <li>Email <input type="email" name="email"></li>
                    
					
				
                </ul>
                <br/>
                <input type="submit" value="ChangeInfo">
            </form>
        </article>
    </body>
</html>
