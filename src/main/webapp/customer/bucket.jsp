<%@ page import="java.util.List" %>
<%@ page import="app.entities.Product" %>
<%@ page import="app.entities.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<h3>Go back to <a href="${pageContext.request.contextPath}/customer/homepage">homepage.</a></h3>
<h3><a href="${pageContext.request.contextPath}/customer/logout">Logout.</a></h3>
<h1>Welcome, ${sessionScope.customer.name}!</h1> </br>
<h2>Here is what you have in your bucket:</h2>

<ul>
   <c:forEach var="product" items="${sessionScope.bucket}">
       <li>
           Name: ${product.productName} </br>
           Price: ${product.productPrice}
       </li>
   </c:forEach>
</ul>
</body>
</html>
