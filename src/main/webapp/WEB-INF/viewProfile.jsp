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
    <h1>Viewing ${viewUserName}'s profile</h1>
    <c:forEach var="ad" items="${viewUserAds}">
        <div class="col-md-6">

            <form action="showinfo" method="post">
                <label for="adInfo">${ad.title}</label>
                <input id="adInfo" name="adInfo" type="submit" value=${ad.id}>
                    <%--<p>${ad.description}</p>--%>
                    <%--<form action="/delete" method="POST">--%>
                    <%--<label for="adId">delete</label>--%>
                    <%--<input id="adId" name="adId" type="submit" class="redButton" value=${ad.id}>--%>
                    <%--</form>--%>
            </form>
        </div>

    </c:forEach>
</div>
<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>
</html>