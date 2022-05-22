<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/coffee-style.css">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="register.title"/></title>
</head>
<body>
<div class="header">
    <h1><fmt:message key="register.header"/></h1>
</div>

<form method="post" id="register-form" action="coffee-servlet">
    <input name="action" value="register" style="visibility: hidden"/><br>
    <label for="login"><fmt:message key="register.login"/></label><br>
    <input name="login" id="login"/><br>
    <label for="password"><fmt:message key="register.password"/></label><br>
    <input name="password" type="password" id="password"/><br>
    <label for="confirm"><fmt:message key="register.confirm"/></label><br>
    <input name="confirm" type="password" id="confirm"/><br><br>
    <button class="big-button" onclick="validateForm()"><fmt:message key="register.sign_up"/></button>
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
<a href="coffee-servlet?action=login"><fmt:message key="register.or_login"/></a>
<p class="footer">© Zmitser Horbach, 2022</p>
</body>
</html>