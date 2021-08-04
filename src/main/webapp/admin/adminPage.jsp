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
    <h5>Note! You can't yet delete category until you delete all products out of category.</h5>
    <form method="post" action="${pageContext.request.contextPath}/admin/manageProductsAndCategories">
        <h4>Categories and their products:</h4>
            <c:forEach var="categoryProductsMap" items="${requestScope.categoryProductsMap}">
                <h2>${categoryProductsMap.key.categoryName}</h2>
                <button type="submit" name="categoryIdToDelete" value="${categoryProductsMap.key.categoryName}">Delete category</button>
                <c:forEach var="product" items="${categoryProductsMap.value}">
                    <h3>Name: ${product.productName}</h3>
                    <button type="submit" name="productIdToDelete" value="${product.productId}">Delete product</button>
                    </br>
                </c:forEach>
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
