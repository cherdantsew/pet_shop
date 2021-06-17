<%--
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
    <p><select name="category">
        <option disabled>Choose product category</option>
        <option value="t1" selected>Чебурашка</option>
        <option value="t2">Крокодил Гена</option>
        <option value="t3">Шапокляк</option>
        <option value="t4">Крыса Лариса</option>
    </select></p>
    <p><input type="submit" value="Отправить"></p>
</form>


</body>
</html>
