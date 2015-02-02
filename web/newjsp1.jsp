<%-- 
    Document   : newjsp1
    Created on : Jan 29, 2015, 10:42:51 AM
    Author     : Moiz
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="GET">
            <jsp:forward page="NewServlet" />
            
        </form>
        
        
    </body>
</html>
