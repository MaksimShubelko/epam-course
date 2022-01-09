<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <link href="/css/button.css" type="text/css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <jsp:include page="header.jsp"/>
    <title>Insert title here</title>
</head>
<body>
<div>
    <form class="container needs-validation" novalidate action="../controller" method="get">
        <input type="hidden" name="command" value="create_account"/>
        <label class="form-label">
            <fmt:message key="registration.welcome.message" bundle="${content}"/>
        </label>

        <div class="row">
            <label class="col-sm-2 col-form-label">
                <fmt:message key="login" bundle="${content}"/>
            </label>
            <div class="col-sm-4">
                <input type="text" name="login"
                       pattern="^[a-z]+([-_]?[a-z0-9]+){0,2}$" required/>
                <div class="invalid-feedback">
                    <fmt:message key="login.error.registration" bundle="${content}"/>
                </div>
            </div>
        </div>

        <div class="row">
            <label class="col-sm-2 col-form-label">
                <fmt:message key="email" bundle="${content}"/>
            </label>

            <div class="col-sm-4">
                <input type="email" name="email"
                       pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"
                       required>
                <div class="invalid-feedback">
                    <fmt:message key="email.error.registration" bundle="${content}"/>
                </div>
            </div>
        </div>

        <div class="row">
            <label class="col-sm-2 col-form-label">
                <fmt:message key="password" bundle="${content}"/>
            </label>

            <div class="col-sm-4">
                <input type="password" name="password"
                       pattern="(?=.*\d)(?=.*\p{Lower})(?=.*\p{Upper})[\d\p{Alpha}]{8,30}" required>
                <div class="invalid-feedback">
                    <fmt:message key="password.error.registration" bundle="${content}"/>
                </div>
            </div>
        </div>

        <div class="row">
            <label class="col-sm-2 col-form-label">
                <fmt:message key="confirm.password" bundle="${content}"/>
            </label>
            <div class="col-sm-4">
                <input type="password" name="password_check" required/>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-4 offset-sm-2">
                <input type="submit" value="<fmt:message key="sing.in" bundle="${content}"/>" class="blubtn">
            </div>
        </div>

    </form>
</div>
<script src="js/validation.js"></script>
</body>
<jsp:include page="footer.jsp"/>
</html>