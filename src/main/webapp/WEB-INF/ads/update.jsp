<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1>Update an Ad</h1>
    <form action="ads/create" method="post">
        <div class="form-group">
            <label for="title">Title</label>
            <input id="title" name="title" class="form-control" type="text" value="${title}">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" class="form-control">${description}</textarea>
        </div>

        <c:forEach var="Category" items="${sessionScope.categories}">
            <input type="checkbox" name="catSelect" value=${Category.id}><c:out value="${Category.catTitle}"><br></c:out></input>
        </c:forEach>
        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>
</body>
</html>