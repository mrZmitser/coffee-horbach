<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/coffee-style.css">
<head>
    <meta charset="UTF-8">
    <title>Preparing...</title>
</head>
<body>
    <div class="header">
        <h1>Coffee</h1>
    </div>
    <h1 id="header">Preparing...</h1>
    <p id="msg">Your drink is preparing right now</p>
    <input class="big-button" type="button" id="proceed" onclick="location.href='coffee-servlet';" style="display: none" value="Back To Start" />
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