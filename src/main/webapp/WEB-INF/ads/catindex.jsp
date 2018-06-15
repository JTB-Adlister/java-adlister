<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% session.setAttribute("currentpage", "ad_categories"); %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>

</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1 class="text-center">${catTitle}</h1>

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
        <ul class="addList text-center">
        <c:forEach var="ad" items="${ads}">
            <li class="addItem">
                <form action="/showinfo" method="post">
                    <input id="adInfo" name="adInfo" type="hidden" value=${ad.id}>
                    <button class="adButton adHover">${ad.title} : ${ad.description}</button>

                </form>
            </li>
        </c:forEach>
        </ul>
    </div>

</div>
<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>

</html>

