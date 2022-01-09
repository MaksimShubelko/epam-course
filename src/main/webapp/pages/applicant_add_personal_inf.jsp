<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="css/button.css" type="text/css" rel="stylesheet">
    <jsp:include page="header.jsp"/>
    <title>Insert title here</title>
</head>
<body>
<div>
    <form class="container needs-validation height-350" novalidate name="loginForm" action="../controller" method="get">
        <input type="hidden" name="command" value="applicant_add_secure_information"/>
        <label class="form-label">
            <fmt:message key="secure.information.title" bundle="${content}"/>
        </label>

        <div class="form-group row w-50">
            <label class="col-sm-2 col-form-label">
                <fmt:message key="secure.information.name" bundle="${content}"/>
            </label>
            <div class="col-sm-10">
                <input type="text" name="name" pattern="^[А-ЯЁ][а-яё]*$" required>
                <div class="invalid-feedback">
                    <fmt:message key="name.secure.information.format.error" bundle="${content}"/>
                </div>
            </div>
        </div>

        <div class="w-100"></div>

        <div class="form-group row w-50">
            <label class="col-sm-2 col-form-label">
                <fmt:message key="secure.information.surname" bundle="${content}"/>
            </label>
            <div class="col-sm-10">
                <input type="text" name="surname"
                       pattern="^[А-ЯЁ][а-яё]*([-][А-ЯЁ][а-яё]*)?$" required>
                <div class="invalid-feedback">
                    <fmt:message key="surname.secure.information.format.error" bundle="${content}"/>
                </div>
            </div>
        </div>

        <div class="form-group row w-50">
            <label class="col-sm-2 col-form-label">
                <fmt:message key="secure.information.lastname" bundle="${content}"/>
            </label>
            <div class="col-sm-10">
                <input type="text" name="lastname" pattern="^[А-ЯЁ][а-яё]*$" required>
                <div class="invalid-feedback">
                    <fmt:message key="lastname.secure.information.format.error" bundle="${content}"/>
                </div>
            </div>
        </div>
        <div class="w-100"></div>

        <div class="w-100"></div>

        <div class="form-group row w-50">
            <label class="col-sm-10 offset-sm-2">
                <input type="submit" value="<fmt:message key="sing.in" bundle="${content}" />"
                       class="blubtn align-middle">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_registration_page" class="hr">
                    <fmt:message key="no.account" bundle="${content}"/>
                </a>
            </label>
        </div>

        <h1>${session_message}</h1>
    </form>
</div>
<script src="js/validation.js"></script>
</body>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</html>