<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <jsp:include page="../header.jsp"/>
    <jsp:include page="/pages/admin/navbar_header.jsp"/>
    <jsp:include page="/pages/admin/navbar_action.jsp"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/button.css" type="text/css" rel="stylesheet">
    <title><fmt:message key="sender.title" bundle="${content}"/></title>
</head>
<body>
<form class="needs-validation h-100" novalidate action="${pageContext.request.contextPath}/controller" method="get">
    <div class="row">
        <div class="col-6 p-5 offset-sm-2">
            <input type="hidden" name="command" value="send_message">
            <input type="hidden" name="email" value="${email}">
            <c:set var="title" value="${title}"/>
            <c:set var="message" value="${message}"/>

            <div class="row">
                <c:if test="${message != null}">
                    <h6 class="text-danger">
                        <fmt:message key="${message}" bundle="${content}"/>
                    </h6>
                </c:if>
                <fmt:message key="admin.sender.page.send.to" bundle="${content}"/>
                <div class="col-3">
                    <h6>${email}</h6>
                </div>
            </div>

            <div class="row">
                <input type="text" class="form-control h-25 m-3"
                       placeholder="<fmt:message key="admin.sender.page.title" bundle="${content}"/>" name="title"
                       value="${title}" aria-placeholder=""
                       aria-describedby="basic-addon1">
                <textarea type="text" class="form-control h-50 m-3"
                          placeholder="<fmt:message key="admin.sender.page.message" bundle="${content}"/>"
                          name="message"
                          aria-describedby="basic-addon1">${message}</textarea>

            </div>
            <div class="row">
                <div class="col-3">
                    <input type="submit" name="submit">
                </div>
                <div class="col-3">
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_show_applicants&page=1">
                        <fmt:message key="btn.back" bundle="${content}"/></a>
                </div>
            </div>

        </div>
    </div>
</form>
</body>
<jsp:include page="../footer.jsp"/>
</html>
