<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% session.setAttribute("currentpage", "search"); %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Search Results" />
    </jsp:include>
</head>
<body>
<%@ include file="/WEB-INF/partials/navbar.jsp" %>

<div class="container">
    <h1>Viewing All Results</h1>
    <h3>${message}</h3>
    <div class="container adContainer col-md-12">
        <ul class="addList text-center">
            <c:forEach var="ad" items="${ads}">
                <li class="addItem">
                    <form action="/showinfo" method="post">
                        <input id="adInfo" name="adInfo" type="hidden" value=${ad.id}>
                        <button class="adButton adHover">${ad.title} : ${ad.description}</button>

                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/partials/scripts.jsp"%>
</body>
</html>