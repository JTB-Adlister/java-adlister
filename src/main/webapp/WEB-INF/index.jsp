<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% session.setAttribute("currentpage", "index"); %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Welcome to my site!" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
    <h1>Welcome to the Adlister!</h1>

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
                    <%--<form action="/ad_categories" method="post" id="catForm">--%>
                        <%--<select id="categoryList" name="categoryList" form="catForm">--%>
                            <%--<c:forEach var="Category" items="${sessionScope.categories}">--%>
                                <%--<option value=${Category.id}><c:out value="${Category.catTitle}"></c:out></option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--<input type="submit">--%>
                    <%--</form>--%>
                </ul>
            </div>
        </div>

    </div>


<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>
</html>










<%--// Comment --%>
