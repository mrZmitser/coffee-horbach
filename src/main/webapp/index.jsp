<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/coffee-style.css">
<head>
    <meta charset="UTF-8">
    <title>Coffee Machine - Welcome!</title>
</head>
<body>
    <div class="header">
        <h1>Coffee</h1>
    </div>
    <div class="menu">
        <a class="active" href="coffee-servlet">Home</a>
        <a href="coffee-servlet?action=buy">Buy</a>
        <c:if test="${role=='admin'}"><a href="coffee-servlet?action=service">Service</a></c:if>
        <c:if test="${not empty role}"><a href="coffee-servlet?action=log-out">Log Out</a></c:if>
        <c:if test="${empty role}"><a href="coffee-servlet?action=login">Log In</a></c:if>
    </div>
    <h1>Welcome to the Coffee Machine, ${(empty user) ? 'stranger' : user}!</h1>
    <p>Buy a cup of delicious coffee here! No need to go anywhere anymore</p>
    <input class="big-button" type="button" onclick="location.href='coffee-servlet?action=buy';" value="BUY IT NOW!" />
    <p class="footer">© Zmitser Horbach, 2022</p>
</body>
</html>