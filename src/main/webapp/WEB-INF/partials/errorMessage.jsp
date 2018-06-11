<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <c:choose>
        <c:when test="${sessionScope.errorMessage != null}">
            <c:forEach var="error" items="${sessionScope.errorMessage}">
                <p>${error}</p>
            </c:forEach>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
</div>