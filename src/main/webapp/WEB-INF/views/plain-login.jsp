<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login page</title>
    <style>
        .failed {
            color: red
        }
    </style>
</head>
<body>
<h3>My Custom Login Page</h3>
<form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">

    <c:if test="${param.error ne null}">
        <i class="failed">Please, enter valid username/password</i>
    </c:if>

    <p>
        User name: <label>
        <input type="text" name="username"/>
    </label>
    </p>
    <p>
        Password: <label>
        <input type="text" name="password"/>
    </label>
    </p>
    <input type="submit" value="Login"/>
</form:form>
</body>
</html>