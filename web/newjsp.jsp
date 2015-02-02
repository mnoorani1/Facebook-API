<%-- 
    Document   : newjsp
    Created on : Jan 27, 2015, 3:58:40 PM
    Author     : Moiz
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="GET">
        <jsp:forward page="checkAccess" />
        </form>
        <%--<a href="https://graph.facebook.com/oauth/authorize?client_id=655619321213160&redirect_uri=http://localhost:8080/FBServlet/newjsp1.jsp&scope=publish_stream,offline_access,create_event,read_stream,publish_actions">Generate Access Token</a><br/>--%>
    </body>
</html>
