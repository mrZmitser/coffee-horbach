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
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<div class="header">
    <h1><fmt:message key="login.header"/></h1>
</div>

<form method="post" action="coffee-servlet">
    <input name="action" value="login" style="visibility: hidden"/><br>
    <label for="login"><fmt:message key="login.login"/></label><br>
    <input name="login" id="login"/><br>
    <label for="password"><fmt:message key="login.password"/></label><br>
    <input name="password" type="password" id="password"/><br><br>
    <button class="big-button" type="submit"><fmt:message key="login.button"/></button>
</form>
<br>
<a href="${pageContext.request.contextPath}/register.jsp"><fmt:message key="login.register"/></a>
<p class="footer">© Zmitser Horbach, 2022</p>
</body>
</html>