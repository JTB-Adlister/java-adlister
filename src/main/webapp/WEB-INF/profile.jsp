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

        <h1>Welcome, ${sessionScope.username}!</h1>
        <div class="create">
            <form action="/updateuser" method="get">
                <input type="submit" value="Update your profile">
            </form>
        </div>
        <br>
    </div>
<%--card that displays user info--%>
    <div class = "container">
        <div class="row">
            <div class="col-12 col-md-4">
                <div class="card">
                    <div class = "card-header textcent">${sessionScope.username}</div>
                    <div class = "card-body textcent">Welcome to your profile page</div>
                    <div class = "card-footer textcent">
                        <form action="/updateuser" method="get">
                            <button name = "update" type = "submit" class = "btn btn-default" value = 1>Change Username</button>
                        </form>
                        <form action="/updateuser" method="get">
                            <button name = "update" type = "submit" class = "btn btn-default" value = 2>Change eMail</button>
                        </form>
                        <form action="/updateuser" method="get">
                            <button name = "update" type = "submit" class = "btn btn-default" value = 3>Change password</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%--displays all ads user has created--%>
    <div class="container adContainer col-md-12">
        <c:forEach var="ad" items="${userAds}">
            <div class="ads col-md-5">
                <form action="showinfo" method="post">
                    <input id="adInfo" name="adInfo" type="hidden" value=${ad.id}>
                    <button class="adButton">${ad.title}</button>
                </form>
            </div>
        </c:forEach>
    </div>

<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>
</html>