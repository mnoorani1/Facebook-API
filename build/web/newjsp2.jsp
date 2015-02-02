<%-- 
    Document   : newjsp2
    Created on : Jan 29, 2015, 12:14:47 PM
    Author     : Moiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form method="POST" action="NewServlet1">
            <span>Post comment on a status on your own wall</span>
            <br/>
            <span>Comment text</span>
            <input type="text" name="wallcomment" />
            <input type ="submit" name="makeWallComment" value="Post New Comment"/><br/><br/>
            <span>Post comment on a status on someone else's wall</span>
            <br/>
            <span>Comment text</span>
            <input type="text" name="statuscomment" />
            <input type ="submit" name="makeStatusComment" value="Post New Comment"/>
        </form>
    </body>
</html>
