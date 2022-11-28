<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <jsp:include page="../header.jsp"/>
    <jsp:include page="../admin/navbar_header.jsp"/>
    <title><fmt:message key="adding.admin.account.title" bundle="${content}"/></title>
</head>
<body>
<div class="font-comforta">
    <form class="needs-validation container h-75 pt-5 col-12 justify-content-center" novalidate
          action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="add_admin_account"/>
        <div class="row w-100 container__content col-4 rounded-3 border-1 offset-4 shadow-lg p-3 mb-5 bg-body rounded border-dark border border-2 bg-opacity-50 rounded-3">
            <label class="align-content-center text-center pe-1 text-uppercase">
                <h6>
                    <fmt:message key="registration.welcome.message" bundle="${content}"/>
                </h6>
            </label>
            <c:if test="${message != null}">
            <h6 class="text-danger ps-0" id="message">
                <fmt:message key="${message}" bundle="${content}"/>
            </h6>
            ${pageContext.request.getSession().removeAttribute("message")}
            </c:if>
            <div class="pt-1">
                <div class="row">
                    <label class="col-sm-2 col-form-label">
                        <h6>
                            <fmt:message key="login" bundle="${content}"/>
                        </h6>
                    </label>
                    <div class="col-6 offset-2 pe-5">
                        <input type="text" name="login"
                               pattern="^[a-z]+([-_]?[a-z0-9]+){0,2}$" required/>
                        <div class="invalid-feedback">
                                <fmt:message key="login.error.registration" bundle="${content}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <label class="col-sm-2 col-form-label">
                        <h6>
                            <fmt:message key="password" bundle="${content}"/>
                        </h6>
                    </label>

                    <div class="col-6 offset-2 pe-5">
                        <input type="password" name="password" id="p1"
                               pattern="(?=.*\d)(?=.*\p{Lower})(?=.*\p{Upper})[\d\p{Alpha}]{8,30}" required>
                        <div class="invalid-feedback">
                                <fmt:message key="password.error.registration" bundle="${content}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <label class="col-sm-2 col-form-label">
                        <h6>
                            <fmt:message key="confirm.password" bundle="${content}"/>
                        </h6>
                    </label>
                    <div class="col-6 offset-2 pe-5">
                        <input type="password" id="p2" name="password_check"
                               pattern="(?=.*\d)(?=.*\p{Lower})(?=.*\p{Upper})[\d\p{Alpha}]{8,30}" required/>
                        <div class="invalid-feedback">
                            <fmt:message key="password.confirm.error" bundle="${content}"/>
                        </div>
                    </div>
                </div>

                <label class="offset-4">
                        <h6>
                            <input type="submit" value="<fmt:message key="sing.up" bundle="${content}"/>"
                                   class="btn-primary border">
                        </h6>
                        <a class="hr border border-info btn-primary"
                           href="${pageContext.request.contextPath}/controller?command=go_to_administrator_main_page">
                            <fmt:message key="btn.back" bundle="${content}"/>
                        </a>
                </label>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
<jsp:include page="../footer.jsp"/>
</html>