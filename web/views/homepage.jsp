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
<h3>here is data for user <%=request.getSession().getAttribute("login")%></h3> <% } %>

<%
    if (request.getSession().getAttribute("logged") == null) {
%>
<h3>here is data for guest</h3>
<h3><a href="/login">Log in</a> to get full access.</h3>
<% } %>

</body>
</html>
