<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration in Kitty land</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Fill in registration data.</h1>
</div>

<div class="w3-container w3-padding">
    <%
        if (request.getAttribute("isAdded") != null && (boolean) request.getAttribute("isAdded")) {
    %>
    <div class="w3-panel w3-green w3-display-container w3-card-4 w3-round">
        <span onclick=this.parentElement.style.display="none" class="w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey"></span>
        <h5>Success! <a href="/login">Try to log in once again.</a> </h5>
    </div>

    <%
        }
    %>

    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Fill in the fields</h2>
        </div>
        <form method="post" class="w3-selection w3-light-grey w3-padding">
            <label>Login:<input type="text" name="login" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Password:
                <input type="password" name="password" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Name:<input type="text" name="name" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Age:<input type="text" name="age" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
</div>

</body>
</html>
