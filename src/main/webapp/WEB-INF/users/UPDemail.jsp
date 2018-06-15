<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1>Please fill in your information.</h1>
    <br>
    <form action="/updateemail" method="post">
        <div class="form-group">
            <label for="email">New Email</label>
            <input id="email" name="email" class="form-control" type="text" placeholder="${email}">
        </div>
        <div class="form-group">
            <label for="passOld">Enter OLD Password for verification</label>
            <p style="color: red">${passmessage1}</p>
            <input id="passOld" name="passOld" class="form-control" type="password">
        </div>
        <input type="submit" class="btn btn-primary btn-block">
    </form>
</div>
<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>
</html>