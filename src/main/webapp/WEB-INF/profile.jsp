<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Welcome, ${sessionScope.username}!</h1>
    <c:forEach var="ad" items="${userAds}">
        <div class="col-md-6">

            <form action="/ads" method="post">
                <label for="adInfo">${ad.title}</label>
                <input id="adInfo" name="adInfo" type="submit" value=${ad.id}>

            </form>
        </div>

    </c:forEach>
</div>

</body>
</html>