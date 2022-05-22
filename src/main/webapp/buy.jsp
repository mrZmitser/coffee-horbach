<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/drinkstaglib.tld" prefix="dt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/coffee-style.css">

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="buy.title"/></title>
</head>
<body>
<div class="header">
    <h1><fmt:message key="buy.header"/></h1>
</div>

<div class="menu">
    <a href="coffee-servlet"><fmt:message key="menu.home"/></a>
    <a class="active" href="coffee-servlet?action=buy"><fmt:message key="menu.buy"/></a>
    <c:if test="${role=='admin'}"><a href="coffee-servlet?action=service"><fmt:message key="menu.service"/></a></c:if>
    <a href="coffee-servlet?action=log-out"><fmt:message key="menu.log_out"/></a>
</div>

<p class="header-like"><fmt:message key="buy.price_list"/></p>
<dt:tableDrinks drinks="${drinks}" role="${role}"/>

<form method="post" action="coffee-servlet">
    <input name="action" value="order" style="visibility: hidden"/>
    <p class="header-like"><fmt:message key="buy.select"/></p>
    <c:forEach items="${drinks}" var="drink">
        <input required type="radio" name="drink" id=${drink.id} value=${drink.id}>
        <label for=${drink.id}>${drink.name}</label><br>
    </c:forEach>
    <br>

    <p class="header-like"><fmt:message key="buy.select_additional"/></p>
    <c:forEach items="${ingrs}" var="ingr">
        <input type="checkbox" name="ingr" id=${ingr.id} value=${ingr.id}>
        <label for=${ingr.id}>${ingr.name}</label>
    </c:forEach>
    <br><br>

    <input class="big-button" type="submit" value="Buy">
</form>
</body>
</html>
