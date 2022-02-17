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
    <title><fmt:message key="profile.title" bundle="${content}"/></title>
</head>
<body>
<div>
    <form class="container needs-validation h-75" novalidate action="${pageContext.request.contextPath}/controller"
          method="get">
        <img class="h-50 offset-6 p-4" src="${img}">

        <div class="offset-4 p-5 w-75">
            <div class="offset-4">
                <h6><fmt:message key="profile.welcome.message" bundle="${content}"/></h6>
            </div>
            <div class="row offset-2">
                <c:choose>
                    <c:when test="${administrator != null}">
                        <div class="row">
                            <div class="col-3">
                                <h6><fmt:message key="secure.information.name" bundle="${content}"/></h6>
                            </div>
                            <div class="col-6">
                                <h6>${administrator.getFirstname()}</h6>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-3">
                                <h6><fmt:message key="secure.information.surname" bundle="${content}"/></h6>
                            </div>
                            <div class="col-6">
                                <h6>${administrator.getSurname()}</h6>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-3">
                                <h6><fmt:message key="secure.information.lastname" bundle="${content}"/></h6>
                            </div>
                            <div class="col-6">
                                <h6>${administrator.getLastname()}</h6>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${applicant != null}">
                        <div class="row">
                            <div class="col-3">
                                <h6><fmt:message key="secure.information.name" bundle="${content}"/></h6>
                            </div>
                            <div class="col-6">
                                <h6>${applicant.getFirstname()}</h6>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-6">
                                <h6><fmt:message key="secure.information.surname" bundle="${content}"/></h6>
                            </div>
                            <div class="col-6">
                                <h6>${applicant.getSurname()}</h6>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-3">
                                <h6><fmt:message key="secure.information.lastname" bundle="${content}"/></h6>
                            </div>
                            <div class="col-6">
                                <h6>${applicant.getLastname()}</h6>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </div>

            <div class="row">
                <div class="col-10 offset-4 p-1">
                    <h6><a href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts">
                        <h6><fmt:message key="btn.back" bundle="${content}"/></h6>
                    </a>
                    </h6>
                </div>
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

