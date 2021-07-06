<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kitty land login</title>
</head>
<body>
<c:if test="${sessionScope.customer == null}">
    <h5>Don't have an account?<a href="${pageContext.request.contextPath}/register">Sign in!</a></h5>
</c:if>

<h2>Log in</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    <label>Login:<input type="text" name="login"><br/>
    </label>
    <label>Password:
        <input type="password" name="password"><br/>
    </label>
    <button type="submit">Submit</button>
</form>
</body>
</html>
