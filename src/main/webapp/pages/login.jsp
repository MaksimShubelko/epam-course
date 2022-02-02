<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/button.css" type="text/css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@600&display=swap" rel="stylesheet">
    <title>Insert title here</title>
</head>
<body>
<div>
    <form name="loginForm" action="${pageContext.request.contextPath}/controller" method="get" class="container h-75">
        <input type="hidden" name="command" value="login"/>
        <div class="row col-6 offset-4 p-lg-5">
            <label class="form-label nav offset-2">
                <h6><fmt:message key="login.welcome.message" bundle="${content}"/></h6>
            </label>
            <c:if test="${message != null}">
                <h6 class="text-danger">
                    <fmt:message key="${message}" bundle="${content}"/>
                </h6>
            </c:if>
            <div class="row">
                <label class="col-sm-2 col-form-label">
                    <h6><fmt:message key="login" bundle="${content}"/></h6>
                </label>
                <div class="col-sm-4">
                    <input type="text" name="login">
                </div>
            </div>

            <div class="row">
                <label class="col-sm-2 col-form-label">
                    <h6><fmt:message key="password" bundle="${content}"/></h6>
                </label>
                <div class="col-sm-4">
                    <input type="password" name="password">
                </div>
            </div>


            <label class="offset-3">
                <h6>
                    <input type="submit" value="<fmt:message key="sing.in" bundle="${content}"/>" class="blubtn">
                </h6>
                <a href="${pageContext.request.contextPath}/controller?command=go_to_registration_page"
                   class="hr border border-info">
                    <fmt:message key="no.account" bundle="${content}"/>
                </a>
            </label>

        </div>
    </form>
</div>
</body>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</html>
