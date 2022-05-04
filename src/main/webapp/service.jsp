<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/cards.css">
<link rel="stylesheet" href="css/coffee-style.css">


<head>
    <meta charset="UTF-8">
    <title>Service</title>
</head>
<body>
    <div class="header">
        <h1>Coffee</h1>
    </div>
    <div class="menu">
        <a href="coffee-servlet">Home</a>
        <a href="coffee-servlet?action=buy">Buy</a>
        <a class="active" href="coffee-servlet?action=service">Service</a>
        <a href="coffee-servlet?action=log-out">Log Out</a>
    </div>
    <h1>Contents</h1>
    <h2>Main Ingredients</h2>
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
                        <button type="submit" class="small-button">Add</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    <h2>Additional Ingredients</h2>
    <c:forEach items="${addIngrs}" var="ingr">
        <div class="column" id="col">
            <div class="card">
                <h4>${ingr.name}</h4>
                <p>${ingr.quantity}</p>
                <form method="post" action="coffee-servlet">
                    <input name="action" value="serviceAddIngr" style="visibility: hidden"/>
                    <input name="id" value="${ingr.id}" style="visibility: hidden"/>
                    <input min="1" type="number" name="quantity" style="width: 50%;">
                    <button type="submit" class="small-button">Add</button>
                </form>
            </div>
        </div>
    </c:forEach>
</body>
</html>