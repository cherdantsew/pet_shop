<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cherdantsev
  Date: 8/9/2021
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage categories</title>
</head>
<body>
<h5>Here you can add/remove categories</h5>
<h2>Note! You can't yet delete category until you delete all products out of category.</h2>
Back to <a href="${pageContext.request.contextPath}/admin/main">main menu.</a>
<hr align="left" width="1000" size="3" color="#ff0000"/>

<form method="post" action="${pageContext.request.contextPath}/admin/category/add">
    <h3>Add new category</h3>
    <h4>Enter new category name: <input type="text" name="newCategoryName">
        <button type="submit">Add category</button>
    </h4>
</form>

<hr align="left" width="1000" size="3" color="#ff0000"/>

<form method="post" action="${pageContext.request.contextPath}/admin/category/remove">
    <h3>Choose category to remove</h3>
        <select name="categoryIdToRemove">
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category.categoryId}">${category.categoryName}</option>
            </c:forEach>
        </select>
    <button type="submit">Remove category</button>
</form>
<c:if test="${requestScope.hasElements == true}">
    <h3>Remove all products to delete the category.</h3>
</c:if>
</body>
</html>
