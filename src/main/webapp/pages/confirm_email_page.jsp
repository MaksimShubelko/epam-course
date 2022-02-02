<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form class="container needs-validation h-75" novalidate action="${pageContext.request.contextPath}/controller"
          method="get">
        <div class="offset-5 p-5">
            <input type="hidden" name="command" value="confirm_email"/>
            <label class="form-label offset-2">
                <h6>
                    <fmt:message key="confirm.email.enter.code" bundle="${content}"/>
                </h6>
            </label>
            <c:if test="${message != null}">
                <h6 class="text-danger">
                    <fmt:message key="${message}" bundle="${content}"/>
                </h6>
            </c:if>
            <input type="hidden" name="login" value="${login}">
            <input type="hidden" name="email" value="${email}">
            <input type="hidden" name="password" value="${password}">
            <input type="hidden" name="password_check" value="${password_check}">
            <input type="hidden" name="ip" value="${ip}">
            <div class="row">
                <div class="col-sm-4 offset-1">
                    <input type="hidden" id="p1" name="email_code_expected" value="${email_code_expected}">
                    <input type="number" id="p2" name="email_code_actual"
                           pattern="^[0-9]{6}$" required/>

                </div>
            </div>

            <div class="row">
                <div class="col-5 offset-2 p-3">
                    <input type="submit" value="<fmt:message key="sing.up.submit.code" bundle="${content}"/>" class="blubtn">
                </div>
            </div>
        </div>
    </form>
</div>
<script src="js/password_confirm.js"></script>
</body>
<jsp:include page="footer.jsp"/>
</html>