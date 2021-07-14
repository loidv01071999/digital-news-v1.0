<%-- 
    Document   : Search Page
    Created on : May 10, 2021, 01:10:22 PM
    Author     : PhatNT
--%>

<%@page import="javax.naming.Context"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Search</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <jsp:include page="Menu.jsp"/>
            <div class="main">

                <div class="left">
                    <c:if test="${pageIndex eq -1}">
                        <h3>${message}</h3>
                    </c:if>
                    <c:if test="${pageIndex ne -1}">
                        <h3 class="number-result">Keyword "${txtSearch}" has ${numberOfResult} results found</h3>
                    </c:if>
                    <c:forEach var="i" items="${listResultSearch}">
                        <div class="clear">
                            <br>
                            <div class="tittle">
                                <a href="DetailDigital?id=${i.id}">
                                    ${i.title}
                                </a>
                            </div>
                            <div class="image-search">
                                <img src="${i.image}"/>
                            </div>
                            <div class="text-search">
                                ${i.shortDes}
                            </div>
                        </div>              
                    </c:forEach>
                    <div class="paging">
                        <c:if test="${maxPage<1}">
                            <h3>Not Found !!</h3>
                        </c:if>
                        <c:if test="${maxPage>1}">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <a class="${i==pageIndex?"active":""}" href="SearchResult?txtPage=${i}&txtSearch=${txtSearch}">${i}</a>
                            </c:forEach>
                        </c:if>
                    </div>
                </div> 
                <jsp:include page="Right.jsp"/>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
