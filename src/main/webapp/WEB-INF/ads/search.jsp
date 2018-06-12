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
<h1>Viewing All Results</h1>
<br>
<div class="container adContainer">
    <h3>${message}</h3>
    <c:forEach var="ad" items="${ads}">
        <div class="ads col-md-5">

                <form action="/showinfo" method="post">
                    <label for="adInfo"><c:out value="${ad.title}"></c:out></label>
                    <input id="adInfo" name="adInfo" type="hidden" value=${ad.id}>
                    <input type="Submit" value="View Ad">

                </form>


        </div>
    </c:forEach>
</div>
<%@ include file="/WEB-INF/partials/scripts.jsp"%>
</body>
</html>