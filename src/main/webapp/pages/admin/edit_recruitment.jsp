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
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <title><fmt:message key="edition.recruitment.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<div>
    <form class="needs-validation container h-auto pt-5 pb-5 col-8 justify-content-center" novalidate
          action="${pageContext.request.contextPath}/controller"
          method="post">
        <input type="hidden" name="command" value="edit_recruitment"/>
        <div class="row w-100 container__content col-8 border-1 offset-3 shadow-lg p-3 mb-5 bg-body rounded border-dark border border-2 bg-opacity-50 rounded-3">
            <label class="align-content-center text-center pe-1 text-uppercase">
                <h6><fmt:message key="admin.main.page.recruitment.update.welcome.message" bundle="${content}"/></h6>
            </label>
            <c:if test="${message != null}">
                <h6 class="text-danger">
                    <fmt:message key="${message}" bundle="${content}"/>
                </h6>
                ${pageContext.request.getSession().removeAttribute("message")}
            </c:if>

            <div class="pt-1">

                <div class="row">
                    <label class="col-sm-2 col-form-label">
                        <h6><fmt:message key="admin.main.page.recruitment.update.finish.date" bundle="${content}"/></h6>
                    </label>
                    <label for="date" class="col-4 offset-2 pe-5">

                    </label>
                    <input type="datetime-local" id="date" name="finish_recruitment" pattern="^d{2}\\.d{2}\\.d{4}Td{2}:d{2}$"
                           value="${recruitment.getFinishRecruitment()}" required/>
                </div>

                <div class="row pt-3">
                    <label class="col-sm-2 col-form-label">
                        <h6>
                            <fmt:message key="admin.main.page.recruitment.update.status" bundle="${content}"/>
                        </h6>
                    </label>
                    <label for="status" class="col-4 offset-2 pe-5">

                    </label>
                    <select id="status" class="custom-select" name="recruitment_status" required>
                        <option value="true">true</option>
                        <option value="false">false</option>
                    </select>
                </div>

                <div class="row pt-3">
                        <h6 class="col-6">
                            <fmt:message key="admin.main.page.recruitment.update.restart.recruitment"
                                         bundle="${content}"/>
                        </h6>
                    <label for="restart" class="col-4 pt-1">
                        <div class="col-6">
                            <input id="restart" type="checkbox" name="restart_recruitment" value="true">
                        </div>
                    </label>
                </div>

                <label class="offset-3 pt-1">
                    <h6>
                        <input type="submit"
                               value="<fmt:message key="admin.main.page.recruitment.update.submit" bundle="${content}" />"
                               class="btn-primary border">
                    </h6>
                    <a class="hr border border-info btn-primary"
                       href="${pageContext.request.contextPath}/controller?command=go_to_administrator_main_page">
                        <fmt:message key="btn.back" bundle="${content}"/>
                    </a>
                </label>
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

