<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>