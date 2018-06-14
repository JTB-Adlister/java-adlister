<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<nav>
    <li><a href="/">Home</a></li>
    <li><a href="/profile">Profile</a></li>
    <li><a href="/ads">Ads</a></li>
    <% request.getSession();
        if (session.getAttribute("pagename") != null){%>


    <%
    }
    %>

    <div class="search">
        <form action="search" method="post">
            <input type="text" name="search" placeholder="Find what you want">
        </form>
    </div>

    <% request.getSession();
        if (session.getAttribute("user") != null){%>

    <li class="login"><a href="/logout">Logout</a></li>
    <li><a href="ads/create">Create Ad</a></li>


    <%
    }
    else { %>

    <button type="button" id="dropdownMenu1" data-toggle="dropdown" class="login btn btn-outline-secondary dropdown-toggle">Login <span class="caret"></span></button>
    <ul class="dropdown-menu dropdown-menu-right mt-2">
        <li class="px-3 py-2">
            <form action="./login" method="POST" class="form" role="form">
                <div class="form-group">
                    <p>Username</p>
                    <input id="username" name="username" placeholder= "Username" class="form-control form-control-sm" type="text" required="">
                </div>
                <div class="form-group">
                    <p>Password</p>
                    <input id="password" name="password" placeholder="Password" class="form-control form-control-sm" type="password" required="">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                </div>
            </form>
            <button class="btn btn-primary btn-block"><a href="/register">Register</a></button>
        </li>
    </ul>


    <%
        }%>
    <c:choose>
        <c:when test="${sessionScope.role.equals('admin')}">
            <a href="/viewUsers">View All Users</a>
        </c:when>
    </c:choose>

</nav>
<div id="modalPassword" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Forgot password</h3>
                <button type="button" class="close font-weight-light" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <p>Reset your password..</p>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                <button class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>


