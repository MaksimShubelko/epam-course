<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <jsp:include page="../header.jsp"/>
    <title><fmt:message key="confirm.email.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<div>
    <form class="needs-validation container h-75 pt-5 col-12 justify-content-center" novalidate action="${pageContext.request.contextPath}/controller"
          method="post">
        <div class="row w-100 container__content col-4 rounded-3 border-1 offset-4 shadow-lg p-3 mb-5 bg-body rounded border-dark border border-2 bg-opacity-50 rounded-3">
            <input type="hidden" name="command" value="confirm_email"/>
            <label class="align-content-center text-center pe-1 text-uppercase">
                <h6>
                    <fmt:message key="confirm.email.enter.code" bundle="${content}"/>
                </h6>
            </label>
            <c:if test="${message != null}">
                <h6 class="text-danger">
                    <fmt:message key="${message}" bundle="${content}"/>
                </h6>
            </c:if>

            <div class="row">
                <div class="offset-3">
                    <input type="hidden" id="p1" name="email_code_expected" value="${email_code_expected}">
                    <input type="number" id="p2" name="email_code_actual"
                           pattern="^[0-9]{6}$" required/>
                    <div class="invalid-feedback">
                            <fmt:message key="email.confirm.error" bundle="${content}"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-5 offset-5 p-2">
                    <input type="submit" value="<fmt:message key="sing.up.submit.code" bundle="${content}"/>" class="btn-primary border">
                </div>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/password_confirm.js"></script>
</body>
<jsp:include page="../footer.jsp"/>
</html>