<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% session.setAttribute("currentpage", "profile"); %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <div class="row">
            <h1 class="centered">Welcome to your profile, ${sessionScope.username}!</h1>
        </div>
        <br>
    </div>
    <div class = "container">
        <div class="row">
            <div class="col-12 col-md-4">
                <div class="card">
                    <div class = "card-header textcent">${sessionScope.username}</div>
                    <div class = "card-body textcent">Welcome to your profile page</div>
                    <div class = "card-footer textcent">
                        <form action="/updateuser" method="get">
                            <input type="submit" value="Update your profile">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container adContainer col-md-12">
        <c:forEach var="ad" items="${userAds}">
            <div class="ads col-md-5">
                <form action="showinfo" method="post">
                    <label for="adInfo">${ad.title}</label>
                    <input id="adInfo" name="adInfo" type="submit" value=${ad.id}>
                </form>
            </div>
        </c:forEach>
    </div>

<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>
</html>