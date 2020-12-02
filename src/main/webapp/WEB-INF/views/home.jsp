<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Company home page</title>
</head>
<body>
<h2>Company home page</h2>
<hr>
<p>
    Welcome to company home page!
</p>
<hr>
<p>
    User: <security:authentication property="principal.username"/>
    <br><br>
    Role(s): <security:authentication property="principal.authorities"/>
</p>

<security:authorize access="hasRole('MANAGER')">
    <hr>
    <p>
        <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
        (Only for managers)
    </p>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
    <hr>
    <p>
        <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
        (Only for admins)
    </p>
</security:authorize>

<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>