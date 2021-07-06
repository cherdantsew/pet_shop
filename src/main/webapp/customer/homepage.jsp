<%@ page import="java.util.List" %>
<%@ page import="app.entities.ProductCategory" %>
<%@ page import="app.entities.Product" %>
<%@ page import="app.repositories.ProductRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Pet Shop Kitty land</title>
</head>
<body>

<h3>Go to <a href="${pageContext.request.contextPath}/customer/bucket">bucket.</a></h3>

<h3><a href="${pageContext.request.contextPath}/customer/logout">Logout.</a></h3>

<% List<ProductCategory> productCategoriesList = (List<ProductCategory>) request.getSession().getAttribute("categories"); %>

<form method="post">
    <c:forEach var="productCategory" items="${sessionScope.categories}">
        <button type="submit" name="chosenCategoryName"
                value="${productCategory.categoryName}">${productCategory.categoryName}
        </button>
    </c:forEach>
</form>

<form method="post">
    <ul>
        <c:forEach var="product" items="${sessionScope.products}">
            <li>Name: ${product.productName} </br>
                Price: ${product.productPrice} </br>
                Description: ${product.productDescription}
                <button type="submit" name="chosenProduct" value="${product.productId}">Add to cart</button>
            </li>
        </c:forEach>
    </ul>
</form>

<c:if test="${requestScope.addedToBucket == true}">
    <section id="successMessage">
        <div>
            <h2>Успешно!</h2>
        </div>
    </section>
    <script type="text/javascript" charset="utf-8">
        setTimeout(() => successMessage.hidden = true, 2000)
    </script>
</c:if>
</body>
</html>
