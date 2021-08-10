<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin homepage</title>
</head>
<body>
<h3>Choose what to do</h3>
<form method="get" action="${pageContext.request.contextPath}/admin/customer">
    <button type="submit">Manage customers</button>
</form>

<form method="get" action="${pageContext.request.contextPath}/admin/category">
    <button type="submit">Manage categories</button>
</form>

<form method="get" action="${pageContext.request.contextPath}/admin/product">
    <button type="submit">Manage products</button>
</form>

</body>
</html>
