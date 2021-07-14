<%-- 
    Document   : HomePage
    Created on : May 10, 2021, 01:10:22 PM
    Author     : PhatNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body> 
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <jsp:include page="Menu.jsp"/>
            <div class="main">
                <div class="left">
                    <c:if test="${mostRecentNew eq null}">
                        <h3>Not Found!</h3>
                    </c:if>
                    <c:if test="${mostRecentNew ne null}">
                        <div class="tittle">
                            ${mostRecentNew.title}
                        </div>
                        <div class="content">
                            <img src="${mostRecentNew.image}"/>
                            <p>${mostRecentNew.description}</p>
                        </div>
                        <div class="signature">
                            <div class="icon1"></div>
                            <div class="icon2"></div>
                            ${mostRecentNew.datePublisher}
                        </div>
                    </c:if>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
