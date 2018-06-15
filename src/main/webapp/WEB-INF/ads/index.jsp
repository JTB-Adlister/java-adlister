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

    <div class="container categories col-md-12">
        <div class="ads col-md-12">
            <ul>
                <form action="/ad_categories" method="post" id="catForm">
                    <c:forEach var="category" items="${categories}">
                        <button name="categoryList" form="catForm" value="${category.id}">
                                ${category.catTitle}
                        </button>
                    </c:forEach>
                </form>
            </ul>
        </div>
    </div>

    <%--<div class="container adContainer col-md-12">--%>
        <%--<%--%>
            <%--List<Object> categories = (List) request.getSession().getAttribute("categories");--%>
            <%--for (Object category : categories){--%>
                <%--Category newCat = (Category) category;--%>
                <%--int id = (int) newCat.getId();--%>
                <%--String title = newCat.getCatTitle();--%>
                <%--request.getSession().setAttribute("thisTitle", title);--%>
                <%--List<Ad> catAds = DaoFactory.getAdsDao().listByCat(id);--%>
                <%--request.getSession().setAttribute("catAds", catAds);--%>
                <%--%>--%>
        <%--<div class="ads col-md-2">--%>
        <%--<h3>${sessionScope.thisTitle}</h3>--%>
            <%--<div>--%>
            <%--<c:forEach var = "ad" items = "${catAds}">--%>
                <%--<form action="/showinfo" method="post" style="clear:both;">--%>
                <%--<input id="adInfo" name="adInfo" type="hidden" value=${ad.id}>--%>
                    <%--<button class="adButton">${ad.title}</button>--%>
                <%--</form>--%>
            <%--</c:forEach>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<%--%>
            <%--}--%>
        <%--%>--%>

    <%--</div>--%>

    <%--<div class="container adContainer col-md-12">--%>
        <%--<c:forEach var="ad" items="${ads}">--%>
            <%--<div class="ads col-md-5">--%>
                <%--<form action="/showinfo" method="post">--%>
                    <%--<label for="adInfo">${ad.title}</label>--%>
                    <%--<input id="adInfo" name="adInfo" type="hidden" value=${ad.id}>--%>
                    <%--<input type="Submit" value="View Ad">--%>
                <%--</form>--%>
            <%--</div>--%>
        <%--</c:forEach>--%>
    <%--</div>--%>

</div>
<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>

</html>
