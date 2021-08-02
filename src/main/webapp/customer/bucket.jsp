<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<h3>Go back to <a href="${pageContext.request.contextPath}/customer/homepage">homepage.</a></h3>
<h3><a href="${pageContext.request.contextPath}/customer/logout">Logout.</a></h3>
<h3>Welcome, ${sessionScope.customer.name}!</h3> </br>

<c:if test="${requestScope.bucket.size() > 0}">
    <h2>Here is what you have in your bucket:</h2>
</c:if>

<c:if test="${requestScope.bucket.size() == 0}">
    <h2>Your bucket is empty.</h2>
</c:if>

<ul>
   <c:forEach var="product" items="${requestScope.bucket}">
       <li>
           Name: ${product.value.productName} </br>
           Price: ${product.value.productPrice} </br>
           Description: ${product.value.productDescription}</br>
           Count: ${product.key}
       </li>
   </c:forEach>
</ul>
</body>
</html>
