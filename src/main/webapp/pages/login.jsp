<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/css/button.css" type="text/css" rel="stylesheet">
    <title>Insert title here</title>
</head>
<body>
<div>
    <form name="loginForm" action="../controller" method="get" class="container">
        <input type="hidden" name="command" value="login"/>
        <label class="form-label nav">
            <fmt:message key="login.welcome.message" bundle="${content}"/>
        </label>

        <div class="row">
            <label class="col-sm-2 col-form-label">
                <fmt:message key="login" bundle="${content}"/>
            </label>
            <div class="col-sm-4">
                <input type="text" name="login">
            </div>
        </div>

        <div class="row">
            <label class="col-sm-2 col-form-label">
                <fmt:message key="password" bundle="${content}"/>
            </label>
            <div class="col-sm-4">
                <input type="password" name="password">
            </div>
        </div>

        <div class="row">
            <label class="col-sm-4 offset-sm-2">
                <input type="submit" value="<fmt:message key="sing.in" bundle="${content}" />"
                       class="blubtn">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_registration_page" class="hr border border-info">
                    <fmt:message key="no.account" bundle="${content}"/>
                </a>
            </label>
        </div>

        <h1>${session_message}</h1>
    </form>
</div>
</body>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</html>