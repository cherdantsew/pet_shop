<%--
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


</body>
</html>
