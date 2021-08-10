<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage products</title>
</head>
<body>
Back to <a href="${pageContext.request.contextPath}/admin/main">main menu.</a>
<h3>Remove product:</h3>
<form method="post" action="${pageContext.request.contextPath}/admin/product/remove">
    <ul>
        <c:forEach var="mapItem" items="${requestScope.categoryWithProductsMap}">
            <li>
                <h4> Category Name: ${mapItem.key.categoryName}
                    <c:forEach var="product" items="${mapItem.value}">
                        <h5>
                            Product name: ${product.productName}
                            <button type="submit" name="productIdToRemove" value="${product.productId}">Remove product
                            </button>
                        </h5>
                    </c:forEach>
                </h4>
            </li>
        </c:forEach>
    </ul>
</form>

<hr align="left" width="1000" size="3" color="#ff0000"/>

<h3>Add product:</h3>
<form method="post" action="${pageContext.request.contextPath}/admin/product/add">
    <h4>1. Choose category for a new product
        <select name="newProductCategoryId">
            <c:forEach var="category" items="${requestScope.categoryWithProductsMap.keySet()}">
                <option value="${category.categoryId}">${category.categoryName}</option>
            </c:forEach>
        </select>
    </h4>
    <h4>2. Enter new product name <input type="text" name="newProductName"></h4>
    <h4>3. Enter product price <input type="text" name="newProductPrice"></h4>
    <h4>3. Enter product description <input type="text" name="newProductDescription"></h4>
    <button type="submit">Add product</button>
</form>

</body>
</html>
