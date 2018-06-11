<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gregslist</title>
    <%@ include file="/WEB-INF/partials/stylelinks.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/partials/navbar.jsp" %>
<h1>Viewing All Results</h1>
<br>
<div class="container adContainer">
    <h3>${message}</h3>
    <c:forEach var="ad" items="${ads}">
        <div class="ads col-md-5">
            <div>
                <h3>${ad.title}</h3>
                <p>${ad.description}</p>
            </div>
        </div>
    </c:forEach>
</div>
<%@ include file="/WEB-INF/partials/scripts.jsp"%>
</body>
</html>