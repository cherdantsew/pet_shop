<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration in Kitty land</title>
</head>
<body>
<c:if test="${requestScope.isAdded}">
    <h5>Success! <a href="${pageContext.request.contextPath}/login">Try to log in once again.</a></h5>
</c:if>


<h1>Fill in the fields.</h1>
<form method="post">
    <label>Login:<input type="text" name="login"><br/>
    </label>
    <label>Password:
        <input type="password" name="password"><br/>
    </label>
    <label>Name:<input type="text" name="name" pattern="[a-z]{1,15}" title="Username should only contain lowercase letters. e.g. john"><br/>
    </label>
    <label>Age:<input type="number" name="age" min="18" title="Only users above 18 can register."><br/>
    </label>
    <button type="submit">Submit</button>
</form>
<a href="${pageContext.request.contextPath}/customer/homepage"><h3>Go to homepage</h3></a>
</body>
</html>
