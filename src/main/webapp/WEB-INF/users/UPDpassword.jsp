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
    <form action="/updatepassword" method="post">
        <div class="form-group">
            <label for="passNew">Enter NEW Password</label>
            <p style="color: red">${passmessage2}</p>
            <p style="color: red">${samepass}</p>
            <input id="passNew" name="passNew" class="form-control" type="password">
        </div>
        <div class="form-group">
            <label for="confirmPass">Confirm NEW Password</label>
            <input id="confirmPass" name="confirmPass" class="form-control" type="password">
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