<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% session.setAttribute("currentpage", "ads"); %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
    <style>
        .redButton{
            color: red;
            background-color: red;
            border-radius: 50%;
            height: 25px;
            width: 25px;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">


    <c:forEach var="user" items="${users}">

        <div class="col-md-6">

            <form action="/viewuser" method="post">
                <label for="userInfo">
                    Username: <c:out value="${user.username}"></c:out>
                Role: <c:out value="${user.role}"></c:out>
                </label>
                <input id="userInfo" name="userInfo" type="hidden" value="${user.username}">
                <input type="Submit" value="View User Profile">
            </form>
        </div>

    </c:forEach>
</div>
<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>

</html>
