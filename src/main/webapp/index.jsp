<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <title><fmt:message key="index.title"/></title>
</head>
<body>
<div class="header">
    <h1><fmt:message key="index.coffee"/></h1>
</div>
<form>
    <select name='lang' onchange='this.form.submit()'>
        <c:choose>
        <c:when test="${userLocale.language == 'ru'}">
        <option value='ru' selected>Русский
        <option value='en'>English
            </c:when>
            <c:otherwise>
        <option value='ru'>Русский
        <option value='en' selected>English
            </c:otherwise>
            </c:choose>
    </select>
</form>
<div class="menu">
    <a class="active" href="coffee-servlet"><fmt:message key="menu.home"/></a>
    <a href="coffee-servlet?action=buy"><fmt:message key="menu.buy"/></a>
    <c:if test="${not empty role}"><a href="coffee-servlet?action=chat"><fmt:message key="menu.chat"/></a></c:if>
    <c:if test="${role=='admin'}"><a href="coffee-servlet?action=service"><fmt:message key="menu.service"/></a></c:if>
    <c:if test="${not empty role}"><a href="coffee-servlet?action=log-out"><fmt:message key="menu.log_out"/></a></c:if>
    <c:if test="${empty role}"><a href="coffee-servlet?action=login"><fmt:message key="menu.log_in"/></a></c:if>
</div>
<h1><fmt:message key="index.header"/> ${(empty user) ? 'stranger' : user}!</h1>
<p><fmt:message key="index.text"/></p>
<input class="big-button" type="button" onclick="location.href='coffee-servlet?action=buy';" value=<fmt:message key="index.buy"/> />
<p class="footer">© Zmitser Horbach, 2022</p>
</body>
</html>
