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
<body>
<div class="container">
    <h1>Please fill in your information.</h1>
    <form action="/updateuser" method="post">
        <div class="form-group">
            <label for="username">New Username</label>
            <input id="username" name="username" class="form-control" type="text">
            <p>${message}</p>
        </div>
        <div class="form-group">
            <label for="email">New Email</label>
            <input id="email" name="email" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="passOld">Enter OLD Password for verification</label>
            <input id="passOld" name="passOld" class="form-control" type="password">
        </div>
        <div class="form-group">
            <label for="passNew">Enter NEW Password</label>
            <input id="passNew" name="passNew" class="form-control" type="password">
        </div>
        <div class="form-group">
            <label for="confirmPass">Confirm NEW Password</label>
            <input id="confirmPass" name="confirmPass" class="form-control" type="password">
        </div>
        <input type="submit" class="btn btn-primary btn-block">
    </form>

<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>
</html>
