<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>

<html>
<head>
    <jsp:include page="../header.jsp"/>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <title><fmt:message key="login.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<div>
    <form name="loginForm" action="${pageContext.request.contextPath}/controller" method="post"
          class="container h-75 pt-5 pb-5 col-12 justify-content-center">
        <input type="hidden" name="command" value="login"/>
        <div class="row w-100 container__content col-4 rounded-3 border-1 offset-4 shadow-lg p-3 mb-5 bg-body rounded border-dark border border-2 bg-opacity-50 rounded-3">
            <label class="align-content-center text-center pe-1 text-uppercase">
                <h6>
                    <fmt:message key="login.welcome.message" bundle="${content}"/>
                </h6>
            </label>
            <c:if test="${message != null}">
                <h6 class="text-danger">
                    <fmt:message key="${message}" bundle="${content}"/>
                </h6>
                ${pageContext.request.getSession().removeAttribute("message")}
            </c:if>
            <div class="pt-1">
                <div class="row">
                    <label class="col-2 col-form-label">
                        <h6><fmt:message key="login" bundle="${content}"/></h6>
                    </label>
                    <div class="col-4 offset-2 pe-5">
                        <input type="text" name="login">
                    </div>
                </div>

                <div class="row">
                    <label class="col-sm-2 col-form-label">
                        <h6><fmt:message key="password" bundle="${content}"/></h6>
                    </label>
                    <div class="col-4 offset-2">
                        <input type="password" name="password">
                    </div>
                </div>

                <label class="offset-3">
                    <h6>
                        <input type="submit" value="<fmt:message key="sing.in" bundle="${content}"/>"
                               class="btn-primary border">
                    </h6>
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_registration_page"
                       class="hr border border-info btn-primary">
                        <fmt:message key="no.account" bundle="${content}"/>
                    </a>
                </label>
            </div>
        </div>
    </form>
</div>
</body>

<footer>
    <jsp:include page="../footer.jsp"/>
</footer>
</html>
