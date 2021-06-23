<%@ page import="java.util.List" %>
<%@ page import="app.entities.Product" %>
<%@ page import="app.entities.Product" %><%--
  Created by IntelliJ IDEA.
  User: cherdantsev
  Date: 6/16/2021
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<h1>Welcome <%=request.getSession().getAttribute("login").equals(null)?"dear guest":request.getSession().getAttribute("login")%></h1> </br>
<h2>Here is what you have in your bucket:</h2>

<%
    List <Product> bucketProductsList = (List) request.getAttribute("bucket");
%>
<ul>
    <%
        for (Product products : bucketProductsList)
            out.println("<li>" + products.toString() + "</li>");
    %>
</ul>
</body>
</html>
