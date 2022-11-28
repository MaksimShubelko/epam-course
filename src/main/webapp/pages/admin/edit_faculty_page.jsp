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
    <title><fmt:message key="edition.faculty.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<div>
    <form class="needs-validation container h-auto pt-5 col-12 justify-content-center" novalidate name="loginForm"
          action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="edit_faculty"/>
        <input type="hidden" name="faculty_id" value="${faculty.getFacultyId()}">
        <div class="row w-100 container__content col-4 rounded-3 border-1 offset-4 shadow-lg p-3 mb-5 bg-body rounded border-dark border border-2 bg-opacity-50 rounded-3">
            <label class="align-content-center text-center pe-1 text-uppercase">
                <h6>
                    <fmt:message key="edit.faculty.page" bundle="${content}"/>
                </h6>
                ${pageContext.request.getSession().removeAttribute("message")}
            </label>
            <c:if test="${message != null}">
            <h6 class="text-danger" id="message">
                <fmt:message key="${message}" bundle="${content}"/>
            </h6>
            </c:if>
            <div>
                ${pageContext.request.getSession().removeAttribute("message")}
            </div>

            <div class="pt-1">
                <div class="row">
                    <label class="col-4 col-form-label">
                        <h6>
                            <fmt:message key="edit.faculty.page.faculty.name" bundle="${content}"/>
                        </h6>
                    </label>
                    <div class="col-5 offset-2 pe-5">
                        <input type="text" name="faculty_name" pattern="^[A-ЯЁ]([а-яё]+\s?)+$"
                               value="${faculty.getFacultyName()}" required>
                        <div class="invalid-feedback">
                            <fmt:message key="faculty.name.error" bundle="${content}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <label class="col-4 col-form-label">
                        <h6>
                            <fmt:message key="edit.faculty.page.faculty.recruitment.plan.free" bundle="${content}"/>
                        </h6>
                    </label>
                    <div class="col-4 offset-2">
                        <input type="number" name="recruitment_plan_free"
                               min="10" max="80" value="${faculty.getRecruitmentPlanFree()}" required>
                        <div class="invalid-feedback">
                            <fmt:message key="faculty.recruitment.plan.error" bundle="${content}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <label class="col-4 col-form-label">
                        <h6>
                            <fmt:message key="edit.faculty.page.faculty.recruitment.plan.canvas" bundle="${content}"/>
                        </h6>
                    </label>
                    <div class="col-4 offset-2">
                        <input type="number" name="recruitment_plan_canvas" min="10" max="80"
                               value="${faculty.getRecruitmentPlanCanvas()}" required>
                        <div class="invalid-feedback">
                            <fmt:message key="faculty.recruitment.plan.error" bundle="${content}"/>
                        </div>
                    </div>
                </div>

                <label class="offset-5">
                    <h6>
                        <input type="submit" value="<fmt:message key="edit.faculty.page.edit" bundle="${content}" />"
                               class="btn-primary border">
                    </h6>
                    <a class="hr border border-info btn-primary"
                       href="${pageContext.request.contextPath}/controller?command=go_to_administrator_main_page">
                        <fmt:message key="btn.back" bundle="${content}"/>
                    </a>
                </label>
            </div>

    </form>
</div>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
<footer>
    <jsp:include page="../footer.jsp"/>
</footer>
</html>

