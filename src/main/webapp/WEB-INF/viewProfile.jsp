<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("currentpage", "viewuser"); %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Viewing ${viewUserName}'s profile</h1>

    <div class="container adContainer col-md-12">
        <c:forEach var="ad" items="${viewUserAds}">
            <div class="ads col-md-5">
                <form action="showinfo" method="post">
                    <input id="adInfo" name="adInfo" type="hidden" value=${ad.id}>
                    <button class="adButton">${ad.title}</button>
                </form>
            </div>
        </c:forEach>
    </div>
    <c:choose>
        <c:when test="${sessionScope.role.equals('admin')}">
            <form method="post" action="/deleteuser">
                <input name="deleteuser" id="deleteuser" type="hidden" value="${viewUserId}">
                <input type="submit" value="Delete User">
            </form>
        </c:when>
    </c:choose>
</div>
<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>
</html>