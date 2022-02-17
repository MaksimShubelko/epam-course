<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/button.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <jsp:include page="../header.jsp"/>
    <title><fmt:message key="adding.admin.account.title" bundle="${content}"/></title>
</head>
<body>
<div>
    <form class="needs-validation h-75" novalidate action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="add_admin_account"/>
        <div class="row bg-warning">
            <div class="col-1 offset-sm-9 border border-success border-3">
                <h6><a href="${pageContext.request.contextPath}/controller?command=go_to_edit_administrator_data">
                    <fmt:message key="administrator.edit.data" bundle="${content}"/>
                </a></h6>
            </div>
            <div class="col-1 border border-success border-3">
                <h6>
                    <a href="${pageContext.request.contextPath}/controller?command=logout">
                        <fmt:message key="logout" bundle="${content}"/>
                    </a>
                </h6>
            </div>
        </div>
        <div class="offset-5">
            <label class="form-label offset-1">
                <h6>
                    <fmt:message key="registration.welcome.message" bundle="${content}"/>
                </h6>
            </label>
            <c:if test="${message != null}">
                <h6 class="text-danger">
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
                            <fmt:message key="password.confirm.error" bundle="${content}"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4 offset-sm-2">
                    <h6>
                        <input type="submit" value="<fmt:message key="sing.up" bundle="${content}"/>" class="blubtn">
                    </h6>
                    <a class="offset-1" href="${pageContext.request.contextPath}/pages/admin/main_page_administrator.jsp">
                        <fmt:message key="btn.back" bundle="${content}"/>
                    </a>
                </div>
            </div>
        </div>

    </form>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
<jsp:include page="../footer.jsp"/>
</html>