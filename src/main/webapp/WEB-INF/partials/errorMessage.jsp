<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <c:choose>
        <c:when test="${errorMessage != null}">
            <c:forEach var="error" items="${errorMessage}">
                <p>${error}</p>
            </c:forEach>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
</div>

<%--push--%>