<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h1>Here Are all the ads!</h1>


    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">

            <form action="/showinfo" method="post">
                <label for="adInfo"><c:out value="${ad.title}"></c:out></label>
                <input id="adInfo" name="adInfo" type="hidden" value=${ad.id}>
                <input type="Submit" value="View Ad">
            </form>
        </div>

    </c:forEach>
</div>

</body>

</html>
