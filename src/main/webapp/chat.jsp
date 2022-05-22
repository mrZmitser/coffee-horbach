<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="chat.title"/></title>
    <link rel="stylesheet" href="./css/coffee-style.css"/>
    <script src="./js/chat.js"></script>
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="menu.home"/></a>
</header>

<h1><fmt:message key="chat.header"/></h1>

<input type="hidden" id="sender" value="${sender}"/>
<input type="hidden" id="role" value="${role}"/>
<div>
    <c:if test="${role == 'admin'}">
        <p>
            <fmt:message key="chat.admin"/>
        </p>
    </c:if>
    <textArea id="chatWindow" rows="10" style="width: 44em;margin: 15px" readonly></textArea>
</div>
<div>
    <input type="text" id="chatInput" style="width: 40em;margin: 15px"/>
    <input id="sendBtn" type="button" class="btn btn-primary-primary" value=
    <fmt:message key="button.send"/> onclick="sendMessage()"/>
</div>

</body>
</html>
