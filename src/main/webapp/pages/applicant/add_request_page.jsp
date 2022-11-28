<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <jsp:include page="../header.jsp"/>
    <jsp:include page="/pages/applicant/navbar_header.jsp"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <title><fmt:message key="adding.request.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<div>
    <form name="loginForm" class="needs-validation container h-auto pt-5 pb-5 col-12 justify-content-center" novalidate
          action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="add_request"/>

        <div class="row w-100 container__content col-4 rounded-3 border-1 offset-4 shadow-lg p-3 mb-5 bg-body rounded border-dark border border-2">

            <div class="row">
                <label class="col-2 col-form-label">
                    <h6>
                        <fmt:message key="request.faculty" bundle="${content}"/>
                    </h6>
                </label>
                <div class="col-8 offset-2 pe-5">
                    <select class="custom-select" name="faculty_id" required>
                        <c:forEach var="faculty" items="${faculties}" varStatus="loop">
                            <option value="${faculty.getFacultyId()}">${faculty.getFacultyName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <label class="col-2 col-form-label">
                    <h6>
                        <fmt:message key="request.benefits" bundle="${content}"/>
                    </h6>
                </label>
                <div class="col-4 offset-2 pe-5">
                    <select class="custom-select" name="privileges" required>
                        <option value="true">
                            <fmt:message key="request.benefits.true" bundle="${content}"/>
                        </option>
                        <option value="false">
                            <fmt:message key="request.benefits.false" bundle="${content}"/>
                        </option>
                    </select>
                </div>
            </div>

            <div class="row">
                <label class="col-2 col-form-label">
                    <h6>
                        <fmt:message key="request.certificate" bundle="${content}"/>
                    </h6>
                </label>
                <div class="col-4 offset-2 pe-5">
                    <input type="text" class="form-control" pattern="^([3-9][.,][0-9]|10.0)$" name="certificate_mark"
                           value="${certificate.getTotalMark()}">
                    <div class="invalid-feedback">
                        <fmt:message key="add.request.page.invalid.certificate" bundle="${content}"/>
                    </div>
                </div>
                <div class="pt-3">
                    <c:forEach var="subject" items="${subjects}" varStatus="loop">
                        <div class="row">
                            <label class="col-2 col-form-label">
                                <c:choose>
                                    <c:when test="${subject.getSubjectType() == 'MATH'}">
                                        <h6><fmt:message key="request.first.profile.subject" bundle="${content}"/></h6>
                                    </c:when>
                                    <c:when test="${subject.getSubjectType() == 'PHYSIC'}">
                                        <h6><fmt:message key="request.second.profile.subject" bundle="${content}"/></h6>
                                    </c:when>
                                    <c:when test="${subject.getSubjectType() == 'ENGLISH'}">
                                        <h6><fmt:message key="request.language" bundle="${content}"/></h6>
                                    </c:when>
                                </c:choose>
                            </label>
                            <div class="col-4 offset-2 pe-5">
                                <input class="form-control" type="number" min="10" max="100"
                                       name="${subject.getSubjectType()}"
                                       value="${subject.getMark()}"/>
                                <div class="invalid-feedback">
                                    <fmt:message key="add.request.page.invalid.subject" bundle="${content}"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <label class="offset-3">
               <h6>
                    <input class="btn-primary border" type="submit" value="<fmt:message key="add.request.page.submit" bundle="${content}"/>">
               </h6>
                <a class="hr border border-info btn-primary"
                       href="${pageContext.request.contextPath}/controller?command=go_to_main_page_applicant">
                        <fmt:message key="btn.back" bundle="${content}"/>
                    </a>
            </label>
        </div>
    </form>
</div>
</body>
<footer>
    <script src="${pageContext.request.contextPath}/js/validation.js"></script>
    <jsp:include page="../footer.jsp"/>
</footer>
</html>
