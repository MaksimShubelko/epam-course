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
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <title><fmt:message key="sender.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<form class="needs-validation container h-auto pt-5 pb-5 col-8 justify-content-center" novalidate
      action="${pageContext.request.contextPath}/controller" method="get">
    <div class="row w-100 container__content col-5 rounded-3 border-1 offset-4 shadow-lg p-3 mb-5 bg-body rounded border-dark border border-2 bg-opacity-50 rounded-3">
        <input type="hidden" name="command" value="send_message">
        <input type="hidden" name="email" value="${email}">
        <c:set var="title" value="${title}"/>
        <c:set var="message" value="${message}"/>

        <div class="row">
            <c:if test="${message != null}">
                <h6 class="text-danger">
                    <fmt:message key="${message}" bundle="${content}"/>
                </h6>
                ${pageContext.request.getSession().removeAttribute("message")}
            </c:if>
            <fmt:message key="admin.sender.page.send.to" bundle="${content}"/>
            <div class="col-3">
                <h6>${email}</h6>
            </div>
        </div>

        <div class="pt-1">
            <div class="row">
                <label>
                    <input type="text" class="col-12 pb-1 mb-1"
                           placeholder="<fmt:message key="admin.sender.page.title" bundle="${content}"/>" name="title"
                           value="${title}">
                </label>
                <label>
                    <textarea type="text" class="col-12 mb-1"
                      placeholder="<fmt:message key="admin.sender.page.message" bundle="${content}"/>"
                      name="message"></textarea>
                </label>
            </div>
            <div class="row">
                <label class="offset-3">
                    <h6>
                        <input type="submit" name="submit" class="btn-primary border"
                               value="<fmt:message key="admin.main.page.applicants.send.message" bundle="${content}"/>">
                    </h6>
                    <a class="hr border border-info btn-primary"
                       href="${pageContext.request.contextPath}/controller?command=go_to_show_applicants&page=1">
                        <fmt:message key="btn.back" bundle="${content}"/></a>
                </label>
            </div>
        </div>
    </div>
</form>
</body>
<jsp:include page="../footer.jsp"/>
</html>
