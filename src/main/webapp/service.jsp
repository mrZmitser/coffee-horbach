<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/cards.css">
<link rel="stylesheet" href="css/coffee-style.css">


<head>
    <meta charset="UTF-8">
    <title><fmt:message key="service.title"/></title>
</head>
<body>
    <div class="header">
        <h1><fmt:message key="service.header"/></h1>
    </div>
    <div class="menu">
        <a href="coffee-servlet"><fmt:message key="menu.home"/></a>
        <a href="coffee-servlet?action=buy"><fmt:message key="menu.buy"/></a>
        <a class="active" href="coffee-servlet?action=service"><fmt:message key="menu.service"/></a>
        <a href="coffee-servlet?action=log-out"><fmt:message key="menu.log_out"/></a>
    </div>
    <h1><fmt:message key="service.contents"/></h1>
    <h2><fmt:message key="service.main"/></h2>
    <div class="row">
        <c:forEach items="${ingrs}" var="ingr">
            <div class="column" id="col">
                <div class="card">
                    <h4>${ingr.name}</h4>
                    <p>${ingr.quantity}</p>
                    <form method="post" action="coffee-servlet">
                        <input name="action" value="serviceIngr" style="visibility: hidden"/>
                        <input name="id" value="${ingr.id}" style="visibility: hidden"/>
                        <input min="1" type="number" name="quantity" style="width: 50%;">
                        <button type="submit" class="small-button"><fmt:message key="service.add"/></button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    <h2><fmt:message key="service.additional"/></h2>
    <c:forEach items="${addIngrs}" var="ingr">
        <div class="column" id="col">
            <div class="card">
                <h4>${ingr.name}</h4>
                <p>${ingr.quantity}</p>
                <form method="post" action="coffee-servlet">
                    <input name="action" value="serviceAddIngr" style="visibility: hidden"/>
                    <input name="id" value="${ingr.id}" style="visibility: hidden"/>
                    <input min="1" type="number" name="quantity" style="width: 50%;">
                    <button type="submit" class="small-button"><fmt:message key="service.add"/>Add</button>
                </form>
            </div>
        </div>
    </c:forEach>
</body>
</html>