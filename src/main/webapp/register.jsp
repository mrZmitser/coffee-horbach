<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/coffee-style.css">
<head>
    <meta charset="UTF-8">
    <title>Coffee - Sign Up</title>
</head>
<body>
<div class="header">
    <h1>Sign Up to the Coffee!</h1>
</div>

<form method="post" id="register-form" action="coffee-servlet">
    <input name="action" value="register" style="visibility: hidden"/><br>
    <label for="login">Login:</label><br>
    <input name="login" id="login"/><br>
    <label for="password">Password:</label><br>
    <input name="password" type="password" id="password"/><br>
    <label for="confirm">Confirm Password:</label><br>
    <input name="confirm" type="password" id="confirm"/><br><br>
    <button class="big-button" onclick="validateForm()">Sign Up!</button>
</form>

<script>
    function validateForm() {
        let form = document.getElementById("register-form")
        let pwd = document.getElementById("password")
        let confirmPwd = document.getElementById("confirm")
        console.log(pwd.innerText)
        console.log(confirmPwd.innerText)
        if (pwd.value !== confirmPwd.value) {
            alert("passwords do not match!");
        } else {
            form.submit()
        }
    }
</script>
<p>${errMsg}</p>
<a href="coffee-servlet?action=login">Or you can log in</a>
<p class="footer">© Zmitser Horbach, 2022</p>
</body>
</html>