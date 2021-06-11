<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="app.entities.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers list</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Future pet shop! 06.09.2021</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Customers</h2>
        </div>
        <%
            HashMap<Integer, Customer> customerHashMap = (HashMap<Integer, Customer>) request.getAttribute("customersSet");
            if (customerHashMap != null && !customerHashMap.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                for (Customer customer : customerHashMap.values()) {
        %>
        <li class=\"w3-hover-sand\"> <%=customer.toString()%> </>
        </li>

        <%
                }
                out.println("</ul>");

            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                    "   <h5>There are no users yet!</h5>\n" +
                    "</div>");

        %>

    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/index.html'">Back to main</button>
</div>
</body>
</html>