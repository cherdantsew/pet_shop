<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin homepage</title>
</head>
<body>
<h3>Choose what to do</h3>
Check out <a href="${pageContext.request.contextPath}/customer/homepage">home page</a>
<form method="post" onchange="this.submit()" action="${pageContext.request.contextPath}/admin/adminPage">
    <select size="2" name="adminAction">
        <option>Manage categories and products</option>
        <option>Manage customers</option>
    </select>
</form>

<c:if test="${requestScope.categoryProductsMap != null}">
    <h5>Here you can add/remove products and categories</h5>
    <h2>Note! You can't yet delete category until you delete all products out of category.</h2>
    <hr align="left" width="1000" size="3" color="#ff0000"/>

    <form method="post" action="${pageContext.request.contextPath}/admin/addProductsAndCategories">
        <h3>Add new category</h3>
        <h4>Enter new category name: <input type="text" name="newCategoryName">
            <button type="submit">Add category</button>
        </h4>
    </form>

    <hr align="left" width="1000" size="3" color="#ff0000"/>

    <form method="post" action="${pageContext.request.contextPath}/admin/addProductsAndCategories">
        <h3>Add new product</h3>
        <h4>1. Choose category for a new product
            <select name="newProductCategoryId">
                <c:forEach var="category" items="${requestScope.categoryProductsMap}">
                    <option value="${category.key.categoryId}">${category.key.categoryName}</option>
                </c:forEach>
            </select>
        </h4>
        <h4>2. Enter new product name <input type="text" name="newProductName"></h4>
        <h4>3. Enter product price <input type="text" name="newProductPrice"></h4>
        <h4>3. Enter product description <input type="text" name="newProductDescription"></h4>
        <button type="submit">Add product</button>
    </form>

    <hr align="left" width="1000" size="3" color="#ff0000"/>

    <form method="post" action="${pageContext.request.contextPath}/admin/deleteProductsAndCategories">
        <h4>Categories and their products:</h4>
        <c:forEach var="categoryProductsMap" items="${requestScope.categoryProductsMap}">
            <h3>Category Name: ${categoryProductsMap.key.categoryName}
                <button type="submit" name="categoryNameToDelete" value="${categoryProductsMap.key.categoryName}">Delete
                    category
                </button>
            </h3>
            <ul>
                <c:forEach var="product" items="${categoryProductsMap.value}">
                    <li>
                        <h4 >Product Name: ${product.productName}
                            <button type="submit" name="productIdToDelete" value="${product.productId}">Delete product</button>
                        </h4>
                    </li>
                </c:forEach>
            </ul>
            <hr align="left" width="500" size="2" color="#ff0000"/>
        </c:forEach>
    </form>
</c:if>

<c:if test="${requestScope.customers != null}">
    Block or unblock customers:
    <form method="post" action="${pageContext.request.contextPath}/admin/manageCustomer">
        <c:forEach var="customer" items="${requestScope.customers}">
            <h3>Id: ${customer.id} Name: ${customer.name} Status: ${customer.isBlocked} Type: ${customer.type}
                <button type="submit" value="${customer.id}" name="customerIdToBlock">Change customer status</button>
                <button type="submit" value="${customer.id}" name="customerIdToAdmin">Change customer type</button>
            </h3>
        </c:forEach>
    </form>
</c:if>

</body>
</html>
