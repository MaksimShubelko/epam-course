<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <jsp:include page="../header.jsp"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/button.css" type="text/css" rel="stylesheet">
    <title><fmt:message key="adding.request.title" bundle="${content}"/></title>
</head>
<body>
<div>
    <form name="loginForm" class="needs-validation w-75 h-100" novalidate
          action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="add_request_command"/>
        <div class="row bg-warning">
            <div class="col-1 offset-sm-9 border border-success border-3">
                <h6><a href="${pageContext.request.contextPath}/controller?command=go_to_edit_administrator_data">
                    <fmt:message key="administrator.edit.data" bundle="${content}"/>
                </a></h6>
            </div>
            <div class="col-1 border border-success border-3">
                <h6>
                    <a href="${pageContext.request.contextPath}/controller?command=logout">
                        <fmt:message key="logout" bundle="${content}"/>
                    </a>
                </h6>
            </div>
        </div>
        <div class="row offset-sm-2">
            <div class="col-6">
                <h6>
                    <fmt:message key="request.faculty" bundle="${content}"/>
                </h6>
                <select class="custom-select" name="faculty_id" required>
                    <c:forEach var="faculty" items="${faculties}" varStatus="loop">
                        <option value="${faculty.getFacultyId()}">${faculty.getFacultyName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-2">
                <h6>
                    <fmt:message key="request.benefits" bundle="${content}"/>
                </h6>
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
            <div class="col-sm-offset-0 col-2">
            </div>
            <div class="col-sm-offset-1 col-2">
                <h6>
                    <fmt:message key="request.certificate" bundle="${content}"/>
                </h6>
                <div class="form-group">
                    <input type="text" class="form-control" pattern="^([3-9][.,][0-9]|10.0)$" name="certificate_mark"
                           value="${certificate.getTotalMark()}">
                    <div class="invalid-feedback">
                        <h6>
                            <fmt:message key="add.request.page.invalid.certificate" bundle="${content}"/>
                        </h6>
                    </div>
                </div>
                <div class="row">
                    <c:forEach var="subject" items="${subjects}" varStatus="loop">
                        <div class="col-8">
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
                            <input class="form-control" type="number" min="10" max="100"
                                   name="${subject.getSubjectType()}"
                                   value="${subject.getMark()}"/>
                            <div class="invalid-feedback">
                                <h6>
                                    <fmt:message key="add.request.page.invalid.subject" bundle="${content}"/>
                                </h6>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-6 offset-5">
                <input type="submit"
                       value="<fmt:message key="add.request.page.submit" bundle="${content}"/>">
            </div>
        </div>
    </form>
</div>
</body>
<footer>
    <script src="${pageContext.request.contextPath}/js/validation.js"></script>
    <jsp:include page="../footer.jsp"/>
</footer>
</html>
