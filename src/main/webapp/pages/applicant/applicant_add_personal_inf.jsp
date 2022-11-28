<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>

<html>
<head>
    <jsp:include page="../header.jsp"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <title><fmt:message key="adding.applicant.personal.inf.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<div>
    <form class="container h-75 pt-5 col-12 justify-content-center needs-validation" novalidate
          action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="applicant_add_secure_information"/>

        <div class="row w-100 container__content col-4 rounded-3 border-1 offset-4 shadow-lg p-3 mb-5 bg-body border-dark border border-2 bg-opacity-50 rounded-3">
            <label class="align-content-center text-center pe-1 text-uppercase">
                <h6>
                    <fmt:message key="secure.information.title" bundle="${content}"/>
                </h6>
            </label>

            <div class="pt-1">
                <div class="row">
                    <label class="col-3 col-form-label">
                        <h6><fmt:message key="secure.information.name" bundle="${content}"/></h6>
                    </label>
                    <div class="col-6">
                        <input type="text" name="name" pattern="[А-ЯЁ][а-яё]+" value="${applicant.getFirstname()}"
                               required/>
                        <div class="invalid-feedback">
                            <fmt:message key="name.secure.information.format.error" bundle="${content}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <label class="col-3 col-form-label">
                        <h6><fmt:message key="secure.information.surname" bundle="${content}"/></h6>
                    </label>
                    <div class="col-6">
                        <input type="text" name="surname"
                               pattern="[А-ЯЁ][а-яё]+([-][А-ЯЁ][а-яё]+)?" value="${applicant.getSurname()}" required>
                        <div class="invalid-feedback">
                            <fmt:message key="surname.secure.information.format.error" bundle="${content}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <label class="col-3 col-form-label">
                        <h6><fmt:message key="secure.information.lastname" bundle="${content}"/></h6>
                    </label>
                    <div class="col-6">
                        <input type="text" name="lastname" pattern="[А-ЯЁ][а-яё]+" value="${applicant.getLastname()}"
                               required>
                        <div class="invalid-feedback">
                            <fmt:message key="lastname.secure.information.format.error" bundle="${content}"/>
                        </div>
                    </div>
                </div>


                <label class="col-10 offset-4 p-1">
                    <h6>
                        <input type="submit"
                               value="<fmt:message key="add.secure.information" bundle="${content}" />"
                               class="btn-primary border"/>
                    </h6>
                    <a class="hr border border-info btn-primary"
                       href="${pageContext.request.contextPath}/controller?command=logout">
                        <fmt:message key="btn.back" bundle="${content}"/>
                    </a>
                </label>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
<footer>
    <jsp:include page="../footer.jsp"/>
</footer>
</html>