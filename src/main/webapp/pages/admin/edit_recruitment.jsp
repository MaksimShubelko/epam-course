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
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/button.css" type="text/css" rel="stylesheet">
    <title><fmt:message key="edition.recruitment.title" bundle="${content}"/></title>
</head>
<body>
<div>
    <form class="needs-validation h-75" novalidate action="${pageContext.request.contextPath}/controller"
          method="get">
        <input type="hidden" name="command" value="edit_recruitment"/>
        <div class="offset-4 p-5 w-75">
            <div class="offset-3 p-4">
                <h6><fmt:message key="admin.main.page.recruitment.update.welcome.message" bundle="${content}"/></h6>
            </div>
            <c:if test="${message != null}">
                <h6 class="text-danger offset-3">
                    <fmt:message key="${message}" bundle="${content}"/>
                </h6>
            </c:if>
            <div class="row">
                <label class="col-3 col-form-label">
                    <h6><fmt:message key="admin.main.page.recruitment.update.finish.date" bundle="${content}"/></h6>
                </label>
                <div class="col-6">
                    <input type="datetime-local" name="finish_recruitment" pattern="^d{2}\\.d{2}\\.d{4}Td{2}:d{2}$"
                           value="${recruitment.getFinishRecruitment()}" required/>
                </div>
            </div>

            <div class="row">
                <label class="col-3 col-form-label">
                    <h6>
                        <fmt:message key="admin.main.page.recruitment.update.status" bundle="${content}"/>
                    </h6>
                </label>
                <select class="custom-select col-4" name="recruitment_status" required>
                    <option value="true">true</option>
                    <option value="false">false</option>
                </select>
            </div>
            <div class="row">
                <div class="col-3">
                    <h6>
                        <fmt:message key="admin.main.page.recruitment.update.restart.recruitment" bundle="${content}"/>
                    </h6>
                </div>
                <div class="col-3">
                    <input type="checkbox" name="restart_recruitment" value="true">
                </div>
            </div>

            <div class="row offset-4 p-1">
                <label class="col-8">
                    <input type="submit"
                           value="<fmt:message key="admin.main.page.recruitment.update.submit" bundle="${content}" />"
                           class="blubtn align-middle">
                </label>

                <a class="row col-8 ps-5"
                   href="${pageContext.request.contextPath}/controller?command=go_to_administrator_main_page">
                    <fmt:message key="btn.back" bundle="${content}"/>
                </a>
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

