<%@ page import="java.util.List" %>
<%@ page import="app.entities.ProductCategory" %>
<%@ page import="app.entities.Product" %><%--
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
<h3>Go to <a href="/bucket">bucket.</a></h3>

<% } else {%>

<h3><a href="/login">Log in</a> to get full access.</h3> <% } %>

<%

%>

<!-- //TODO
1. LIST OF CATEGORIES IS SENT WITH ATTRIBUTE NAME "categories"
2. SEND CHOSEN CATEGORY BY METHOD POST IN PARAMETER WITH NAME "chosenCategoryName" SO AS TO GET LIST OF ITEMS WITH REQUIRED CATEGORY -->

<form method="post">
    <p><select name="chosenCategoryName">
        <%
            List<ProductCategory> productCategoriesList = (List<ProductCategory>) request.getAttribute("categories");
            for (ProductCategory category : productCategoriesList) { %>
        <option value="<%=category.getCategory_name()%>" selected><%=category.getCategory_name()%>
        </option>
        <% } %>
    </select></p>
    <p><input type="submit" value="Show products"></p>
</form>

<% if (request.getAttribute("products") != null) {%>
<form method="post">
    <ul>
        <%
            List<Product> productList = (List<Product>) request.getAttribute("products");
            for (Product product : productList) { %>
        <li><%=product.toString()%>
            <button type="submit" name="chosenProduct" value="<%=product.getProduct_id()%>">Add to cart</button>
        </li>


        <%}%>
    </ul>
</form>
<% } %>

</body>
</html>
