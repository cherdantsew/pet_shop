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
<form method="post" onchange="this.submit()" action="${pageContext.request.contextPath}/admin/adminPage">
    <select size="2" name="adminAction">
        <option>Manage categories and products</option>
        <option>Block customer</option>
    </select>
</form>

<c:if test="${requestScope.categoryProductsMap != null}">
    <h5>Here you can add/remove products and categories</h5>
    <h2>Note! You can't yet delete category until you delete all products out of category.</h2>
    <hr align="left" width="1000" size="3" color="#ff0000"/>

    <form method="post" action="${pageContext.request.contextPath}/admin/addProductsAndCategories">
        <h3>Add new category</h3>
        <h4>Enter new category name:</h4>
        <input type="text" name="newCategoryName">
        <button type="submit">Add category</button>
    </form>

    <hr align="left" width="1000" size="3" color="#ff0000"/>

    <form method="post" action="${pageContext.request.contextPath}/admin/addProductsAndCategories">
        <h3>Add new product</h3>
        <h4>1. Choose category for a new product</h4>
        <select name="newProductCategoryId">
            <c:forEach var="category" items="${requestScope.categoryProductsMap}">
                <option value="${category.key.categoryId}">${category.key.categoryName}</option>
            </c:forEach>
        </select>
        <h4>2. Enter new product name</h4>
        <input type="text" name="newProductName">
        <h4>3. Enter product price</h4>
        <input type="text" name="newProductPrice">
        <h4>3. Enter product description</h4>
        <input type="text" name="newProductDescription">
        <button type="submit">Add product</button>
    </form>

    <form method="post" action="${pageContext.request.contextPath}/admin/deleteProductsAndCategories">
        <h4>Categories and their products:</h4>
        <c:forEach var="categoryProductsMap" items="${requestScope.categoryProductsMap}">
            <h2>Category Name: ${categoryProductsMap.key.categoryName}</h2>
            <button type="submit" name="categoryNameToDelete" value="${categoryProductsMap.key.categoryName}">Delete
                category
            </button>
            <c:forEach var="product" items="${categoryProductsMap.value}">
                <h3>Product Name: ${product.productName}</h3>
                <button type="submit" name="productIdToDelete" value="${product.productId}">Delete product</button>
            </c:forEach>
            <hr align="left" width="500" size="2" color="#ff0000"/>
        </c:forEach>
    </form>
</c:if>

<c:if test="${requestScope.isCategoryDeleted == true}">
    <section id="categoryDeleteSuccessMessage">
        <div>
            <h2>Category was deleted successfully!</h2>
        </div>
    </section>
    <script type="text/javascript" charset="utf-8">
        setTimeout(() => categoryDeleteSuccessMessage.hidden = true, 2000)
    </script>
</c:if>
<c:if test="${requestScope.isProductDeleted == true}">
    <section id="productDeleteSuccessMessage">
        <div>
            <h2>Product was deleted successfully!</h2>
        </div>
    </section>
    <script type="text/javascript" charset="utf-8">
        setTimeout(() => productDeleteSuccessMessage.hidden = true, 2000)
    </script>
</c:if>
</body>
</html>
