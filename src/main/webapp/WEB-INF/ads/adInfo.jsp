<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% session.setAttribute("currentpage", "showinfo");%>
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
<jsp:include page="/WEB-INF/partials/navbar.jsp" /></body>

<div class="container">

    <%--<c:forEach var="showAd" items="${showAd}">--%>
    <div class="col-md-6">
        <h1><c:out value ="${showAd.title}"></c:out></h1>
        <%--<h5>Posted by: ${sessionScope.showAd.userid}</h5>--%>
        <h6>Description: </h6>
        <p><c:out value ="${showAd.description}"></c:out></p>
        <% request.getSession();
            if (session.getAttribute("userId") != session.getAttribute("adUserId")){%>
            <form action="/ads" method="POST">
            <input id="viewUser" name="viewUser" type="hidden" value="${adUserId}">
            <input type="submit" value="View Poster">
        </form>

        <%
        }
        else { %>

        <form action="/delete" method="POST">
            <input id="adId" name="adId" type="hidden" value=${showAd.id}>
            <input type="submit" value="Delete Ad">

        </form>

        <form action="update" method="post">
            <input id="adInfo" name="adInfo" type="hidden" value="${showAd.id}">
            <input type="submit" value="Update Ad">
        </form>
        <%
            }%>
    </div>
    <%--</c:forEach>--%>
</div>
<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>

</html>
