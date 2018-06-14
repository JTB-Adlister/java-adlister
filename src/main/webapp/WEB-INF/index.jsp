<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("pagename", "home"); %>
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

        <div id="demo" class="carousel slide" data-ride="carousel">
            <div class="container categories col-md-12">
                <h3 style="text-align: center;">Featured Ads</h3>

                <ul class="carousel-indicators">
                    <li data-target="#demo" data-slide-to="0" class="active"></li>
                    <li data-target="#demo" data-slide-to="1"></li>
                    <li data-target="#demo" data-slide-to="2"></li>
                </ul>

                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div>
                            <form action="/showinfo" method="post" style="clear:both;">
                            <input name="adInfo" type="hidden" value=${random[0].id}>
                                <button class="adButton"><h1>${random[0].title}</h1></button>
                            </form>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div>
                            <form action="/showinfo" method="post" style="clear:both;">
                                <input name="adInfo" type="hidden" value=${random[1].id}>
                                <button class="adButton"><h1>${random[1].title}</h1></button>
                            </form>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div>
                            <form action="/showinfo" method="post" style="clear:both;">
                                <input name="adInfo" type="hidden" value=${random[2].id}>
                                <button class="adButton"><h1>${random[2].title}</h1></button>
                            </form>
                        </div>
                    </div>
                </div>

                <a class="carousel-control-prev" href="#demo" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demo" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>


        </div>



    </div>


<%@ include file="/WEB-INF/partials/scripts.jsp" %>
</body>
</html>










<%--// Comment --%>
