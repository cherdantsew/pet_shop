<%@ page import="java.util.List" %>
<%@ page import="app.entities.ProductCategory" %>
<%@ page import="app.entities.Product" %>
<%@ page import="app.repositories.ProductRepository" %><%--
  Created by IntelliJ IDEA.
  User: cherdantsev
  Date: 6/12/2021
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pet Shop Kitty land</title>
</head>
<body>
<%
    if (request.getSession().getAttribute("logged") != null) {
%>
<h3>Go to <a href="${pageContext.request.contextPath}/bucket">bucket.</a></h3>

<% } else {%>

<h3><a href="${pageContext.request.contextPath}/login">Log in</a> to get full access.</h3> <% } %>

<% List<ProductCategory> productCategoriesList = (List<ProductCategory>) request.getSession().getAttribute("categories"); %>

<form method="post">

    <% for (ProductCategory productCategory : productCategoriesList) { %>
    <button type="submit" name="chosenCategoryName"
            value="<%=productCategory.getCategoryName()%>"><%=productCategory.getCategoryName()%>
    </button>
    <% } %>

</form>

<% if (request.getSession().getAttribute("products") != null) {%>
<form method="post">
    <ul>
        <%
            List<Product> productList = (List<Product>) request.getSession().getAttribute("products");
            for (Product product : productList) { %>
        <li>Name: <%=product.getProductName()%> </br>
            Price: <%=product.getProductPrice()%> </br>
            Description: <%=product.getProductDescription()%>
            <% if (request.getSession().getAttribute("logged") != null && (boolean) request.getSession().getAttribute("logged")) { %>
            <button type="submit" name="chosenProduct" value="<%=product.getProductId()%>">Add to cart</button>

            <% } %>
        </li>


        <%}%>
    </ul>
</form>
<% } %>

<% if (request.getAttribute("addedToBucket") != null && (boolean) request.getAttribute("addedToBucket")){ %>
<section id="successMessage">
    <div>
        <h2>Успешно!</h2>
    </div>
</section>
<script type="text/javascript" charset="utf-8">
    setTimeout(() => successMessage.hidden = true, 2000)
</script>
<% } %>

</body>
</html>
