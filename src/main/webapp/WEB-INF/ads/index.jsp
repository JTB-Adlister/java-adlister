<%@ page import="java.util.List" %>
<%@ page import="com.codeup.adlister.models.Category" %>
<%@ page import="com.codeup.adlister.models.Ad" %>
<%@ page import="com.codeup.adlister.dao.DaoFactory" %>
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
    <h1>Here Are all the ads!</h1>

    <div class="create">
        <form action="/ad_categories" method="post" id="catForm">
            <select id="categoryList" name="categoryList" form="catForm">
                <c:forEach var="Category" items="${sessionScope.categories}">
                    <option value=${Category.id}><c:out value="${Category.catTitle}"></c:out></option>
                </c:forEach>
            </select>
            <input type="submit">
        </form>
    </div>

    <div class="container adContainer col-md-12">
        <c:forEach var="ad" items="${ads}">
            <div class="ads col-md-5">
                <form action="/showinfo" method="post">
                    <label for="adInfo"><c:out value="${ad.title}"></c:out></label>
                    <input id="adInfo" name="adInfo" type="hidden" value=${ad.id}>
                    <input type="Submit" value="View Ad">
                </form>
            </div>
        </c:forEach>
    </div>

</div>
<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>

</html>
