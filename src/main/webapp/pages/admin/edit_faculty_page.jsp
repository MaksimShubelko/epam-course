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
    <title><fmt:message key="edition.faculty.title" bundle="${content}"/></title>
</head>
<body>
<div>
    <form class="container needs-validation h-75" novalidate name="loginForm"
          action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="edit_faculty"/>
        <input type="hidden" name="faculty_id" value="${faculty.getFacultyId()}">
        <div class="p-5 offset-3">
            <div class="offset-4 p-3">
                <h6>
                    <fmt:message key="edit.faculty.page" bundle="${content}"/>
                </h6>
            </div>
            <div class="offset-3 p-3">
                <c:if test="${message != null}">
                    <h6 class="text-danger">
                        <fmt:message key="${message}" bundle="${content}"/>
                    </h6>
                </c:if>
            </div>
            <div class="row offset-1">
                <label class="col-4 col-form-label">
                    <h6>
                        <fmt:message key="edit.faculty.page.faculty.name" bundle="${content}"/>
                    </h6>
                </label>
                <div class="col-8">
                    <input type="text" name="faculty_name" pattern="^[A-ЯЁ]([а-яё]+\s?)+$"
                           value="${faculty.getFacultyName()}" required>
                    <div class="invalid-feedback">
                        <h6>
                            <fmt:message key="faculty.name.error" bundle="${content}"/>
                        </h6>
                    </div>
                </div>
            </div>

            <div class="row offset-1">
                <label class="col-4 col-form-label">
                    <h6>
                        <fmt:message key="edit.faculty.page.faculty.recruitment.plan.free" bundle="${content}"/>
                    </h6>
                </label>
                <div class="col-8">
                    <input type="number" name="recruitment_plan_free"
                           min="10" max="80" value="${faculty.getRecruitmentPlanFree()}" required>
                    <div class="invalid-feedback">
                        <h6>
                            <fmt:message key="faculty.recruitment.plan.error" bundle="${content}"/>
                        </h6>
                    </div>
                </div>
            </div>

            <div class="row offset-1">
                <label class="col-4 col-form-label">
                    <h6>
                        <fmt:message key="edit.faculty.page.faculty.recruitment.plan.canvas" bundle="${content}"/>
                    </h6>
                </label>
                <div class="col-8">
                    <input type="number" name="recruitment_plan_canvas"
                           min="10" max="80" value="${faculty.getRecruitmentPlanCanvas()}" required>
                    <div class="invalid-feedback">
                        <h6>
                            <fmt:message key="faculty.recruitment.plan.error" bundle="${content}"/>
                        </h6>
                    </div>
                </div>
            </div>

            <div class="row offset-4 p-1">
                <label class="col-8">
                    <input type="submit" value="<fmt:message key="edit.faculty.page.edit" bundle="${content}" />"
                           class="blubtn align-middle">
                </label>
                <a href="${pageContext.request.contextPath}/controller?command=go_to_show_faculties&page=${page}"
                   class="row col-8 ps-5">
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

