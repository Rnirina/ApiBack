<%-- 
    Author     : RADO RANDRIAMANANA
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "mg.itu.tpt.databaseConnection.DbConnect" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/ApiBack/Login" method="POST">
            <input type="text" name="Username"/>
            <input type="password" name="Password"/>
            <button type="submit" value="Submit">Envoyer</button>
        </form>
    </body>
</html>
