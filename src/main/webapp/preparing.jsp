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
    <title><fmt:message key="preparing.title"/></title>
</head>
<body>
    <div class="header">
        <h1>Coffee</h1>
    </div>
    <h1 id="header"><fmt:message key="preparing.preparing"/></h1>
    <p id="msg"><fmt:message key="preparing.text"/></p>
    <input class="big-button" type="button" id="proceed" onclick="location.href='coffee-servlet';" style="display: none" value=<fmt:message key="preparing.back_to_start"/> />
    <script>
        function showReadyMessage() {
            document.getElementById("proceed").style.display = "inline";
            document.getElementById("header").innerText = "Have a nice day!";
            document.getElementById("msg").innerText = "Take your drink!";
        }
        setTimeout(showReadyMessage, 5000);
    </script>
</body>
</html>