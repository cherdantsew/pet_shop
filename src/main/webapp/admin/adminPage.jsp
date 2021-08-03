<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cherdantsev
  Date: 8/3/2021
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin homepage</title>
</head>
<body>
<h3>Choose what to do</h3>
<form method="post" onchange="this.form.submit()">
    <select size="2" name="adminAction">
        <option>Manage categories and products</option>
        <option>Block customer</option>
    </select>
</form>

<c:if test="${(requestScope.categories && requestScope.products) != null}">
    <h5>Here you can add/remove products and categories</h5>
    <form method="post" action="${pageContext.request.contextPath}/admin/manageProductsAndCategories">
        <h4>Categories and their products:</h4>

            <c:forEach var="category" items="${requestScope.categories}">
                <h5>${category.categoryName}</h5><button type="submit" name="categoryIdToDelete" value="${category.categoryId}">Delete</button>
            </c:forEach>

    </form>
</c:if>


</body>
</html>
