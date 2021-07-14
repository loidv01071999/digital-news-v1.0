<%-- 
    Document   : Error
    Created on : May 10, 2021, 01:10:22 PM
    Author     : PhatNT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <jsp:include page="Menu.jsp"/>
            <div class="content">
                <div class="main">
                    <h1>${exceptionMessage}}</h1>
                </div>
                <jsp:include page="Right.jsp"/> 
            </div>
            
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
