<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <title>Error 404</title>
</head>
<body>
<h2>
    <fmt:message key="blocked.account.message" bundle="${content}"/>
</h2>
<b><a href="${pageContext.request.contextPath}/controller?command=go_to_login_page">
    <fmt:message key="blocked.account.go.login.page" bundle="${content}"/>
</a>
</b>
</body>
</html>