<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/button.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <jsp:include page="../header.jsp"/>
    <title><fmt:message key="registration.title" bundle="${content}"/></title>
</head>
<body>
<div>
    <form class="container needs-validation h-75" novalidate action="${pageContext.request.contextPath}/controller"
          method="get">
        <input type="hidden" name="command" value="create_account"/>
        <div class="row col-8 offset-4 p-lg-5 container__content">
            <label class="form-label offset-1">
                <h6>
                    <fmt:message key="registration.welcome.message" bundle="${content}"/>
                </h6>
            </label>
            <c:if test="${message != null}">
                <h6 class="text-danger offset-1">
                    <fmt:message key="${message}" bundle="${content}"/>
                </h6>
            </c:if>
            <div class="row">
                <label class="col-sm-2 col-form-label">
                    <h6>
                        <fmt:message key="login" bundle="${content}"/>
                    </h6>
                </label>
                <div class="col-sm-4">
                    <input type="text" name="login"
                           pattern="^[a-z]+([-_]?[a-z0-9]+){0,2}$" required/>
                    <div class="invalid-feedback">
                        <h6>
                            <fmt:message key="login.error.registration" bundle="${content}"/>
                        </h6>
                    </div>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-2 col-form-label">
                    <h6>
                        <fmt:message key="email" bundle="${content}"/>
                    </h6>
                </label>

                <div class="col-sm-4">
                    <input type="email" name="email"
                           pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"
                           required>
                    <div class="invalid-feedback">
                        <h6>
                            <fmt:message key="email.error.registration" bundle="${content}"/>
                        </h6>
                    </div>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-2 col-form-label">
                    <h6>
                        <fmt:message key="password" bundle="${content}"/>
                    </h6>
                </label>

                <div class="col-sm-4">
                    <input type="password" name="password" id="p1"
                           pattern="(?=.*\d)(?=.*\p{Lower})(?=.*\p{Upper})[\d\p{Alpha}]{8,30}" required>
                    <div class="invalid-feedback">
                        <h6>
                            <fmt:message key="password.error.registration" bundle="${content}"/>
                        </h6>
                    </div>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-2 col-form-label">
                    <h6>
                        <fmt:message key="confirm.password" bundle="${content}"/>
                    </h6>
                </label>
                <div class="col-sm-4">
                    <input type="password" id="p2" name="password_check"
                           pattern="(?=.*\d)(?=.*\p{Lower})(?=.*\p{Upper})[\d\p{Alpha}]{8,30}" required/>
                    <div class="invalid-feedback">
                        <h6>
                            <fmt:message key="password.confirm.error" bundle="${content}"/>
                        </h6>
                    </div>
                </div>
            </div>

            <div class="row offset-4 p-1">
                <div class="col-8">
                    <h6>
                        <input type="submit" value="<fmt:message key="sing.up" bundle="${content}"/>"
                               class="blubtn align-middle col-8">
                    </h6>
                </div>
                <a class="row col-8 ps-5" href="${pageContext.request.contextPath}/controller?command=go_to_login_page">
                    <fmt:message key="btn.back" bundle="${content}"/>
                </a>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
<jsp:include page="../footer.jsp"/>
</html>