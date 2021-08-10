<%@ page import="app.entities.Customer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cherdantsev
  Date: 8/9/2021
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Manage customers</title>
</head>
<body>
Back to <a href="${pageContext.request.contextPath}/admin/main">main menu.</a>

<h4>Block or unblock customers:</h4>
<c:forEach var="customer" items="${requestScope.customers}">
    <h3>Id: ${customer.id} Name: ${customer.name} Status: ${customer.isBlocked} Type: ${customer.type}
        <form method="post" action="${pageContext.request.contextPath}/admin/customer/block">
            <c:if test="${Customer.TYPE_BLOCKED.equals(customer.isBlocked)}">
                <button type="submit" value="${customer.id}" name="customerIdToBlock">Unblock customer</button>
            </c:if>
            <c:if test="${Customer.TYPE_UNBLOCKED.equals(customer.isBlocked)}">
                <button type="submit" value="${customer.id}" name="customerIdToBlock">Block customer</button>
            </c:if>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/admin/customer/assignAdmin">
            <c:if test="${Customer.ROLE_ADMIN.equals(customer.type)}">
                <button type="submit" value="${customer.id}" name="customerIdToChangeRole">Remove from admins</button>
            </c:if>
            <c:if test="${Customer.ROLE_CUSTOMER.equals(customer.type)}">
                <button type="submit" value="${customer.id}" name="customerIdToChangeRole">Grant admin role</button>
            </c:if>
        </form>
    </h3>
</c:forEach>
</body>
</html>
