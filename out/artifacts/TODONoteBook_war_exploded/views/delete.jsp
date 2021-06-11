<%@ page import="java.util.List" %>
<html>

<head>
    <title>Delete customer</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">

<div class=" w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>

<div class="w3-container w3-padding">
    <%
        if (request.getAttribute("isDeleted") != null && request.getAttribute("isDeleted").equals("true")) {
            System.out.println(request.getParameter("isDeleted"));
            out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n" +
                    "   <h5>Customer was deleted!</h5>\n" +
                    "</div>");
        }
    %>
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Remove customer</h2>
        </div>
        <div class="w3-card-4">
        <div class="w3-container w3-center w3-blue">
            <h3>Users available</h3>
        </div>
        <%
            List<String> names = (List<String>) request.getAttribute("usersList");

            if (names != null && !names.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                for (String s : names) {
                    out.print("<li class=\"w3-hover-sand\">" + s + "</li>");
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

        <form method="post" class="w3-selection w3-light-grey w3-padding">
            <label>User id to remove:
                <input type="text" name="name" class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
                <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Delete</button>
            </label>
        </form>
    </div>

    <div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <button class="w3-btn w3-round-large" onclick="location.href='/index.html'">Back to main</button>
    </div>


</div>
</body>
</html>