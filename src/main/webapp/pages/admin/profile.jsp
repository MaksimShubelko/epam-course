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
    <title><fmt:message key="profile.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<div>
    <%--<form class="container h-auto pt-5 pb-5 col-12 d-flex justify-content-center"
          action="${pageContext.request.contextPath}/controller"
          method="get">
        <div class="row container__content col-4 shadow-lg p-3 mb-5 bg-body border border-dark bg-opacity-50 rounded-3">
                <img class="h-auto col-8 offset-2 p-0 border border-1 border-dark" src="${img}">
            <div class="pt-1">
                <div class="offset-4 p-3">
                    <h6><fmt:message key="profile.welcome.message" bundle="${content}"/></h6>
                </div>
                <c:choose>
                    <c:when test="${administrator != null}">
                        <div class="row">
                            <label class="col-4 col-form-label">
                                <h6><fmt:message key="secure.information.name" bundle="${content}"/></h6>
                            </label>
                            <div class="col-6 offset-1">
                                <h6>${administrator.getFirstname()}</h6>
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-4 col-form-label">
                                <h6><fmt:message key="secure.information.surname" bundle="${content}"/></h6>
                            </label>
                            <div class="col-6 offset-1">
                                <h6>${administrator.getSurname()}</h6>
                            </div>

                        </div>
                        <div class="row">
                            <label class="col-4 col-form-label">
                                <h6><fmt:message key="secure.information.lastname" bundle="${content}"/></h6>
                            </label>
                            <div class="col-6 offset-1">
                                <h6>${administrator.getLastname()}</h6>
                            </div>
                        </div>
                    </c:when>

                    <c:when test="${applicant != null}">
                        <div class="row">
                            <label class="col-4 col-form-label">
                                <h6><fmt:message key="secure.information.name" bundle="${content}"/></h6>
                            </label>
                            <div class="col-6 offset-1">
                                <h6>${applicant.getFirstname()}</h6>
                            </div>
                        </div>

                        <div class="row">
                            <label class="col-4 col-form-label">
                                <h6><fmt:message key="secure.information.surname" bundle="${content}"/></h6>
                            </label>
                            <div class="col-6 offset-1">
                                <h6>${applicant.getSurname()}</h6>
                            </div>
                        </div>

                        <div class="row">
                            <label class="col-4 col-form-label">
                                <h6><fmt:message key="secure.information.lastname" bundle="${content}"/></h6>
                            </label>
                            <div class="col-6 offset-1">
                                <h6>${applicant.getLastname()}</h6>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
                <a class="hr row offset-3 col-3 border border-info btn-primary"
                   href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts">
                    <fmt:message key="btn.back" bundle="${content}"/>
                </a>
            </div>
        </div>
    </form>--%>
        <form class="container h-auto pt-5 pb-5 col-12 d-flex justify-content-center"
              action="${pageContext.request.contextPath}/controller" method="post">
            <div class="row container__content col-4 shadow-lg p-3 mb-5 bg-body border border-dark bg-opacity-50 rounded-3">
                    <img class="h-auto col-8 p-0 offset-2 border border-1 border-dark" src="${img}">
                <div class="pt-1">
                    <div class="offset-4 p-3">
                        <h6><fmt:message key="edit.administrator.data.welcome.message" bundle="${content}"/></h6>
                    </div>
                    <div class="row">
                        <label class="col-4 col-form-label">
                            <h6><fmt:message key="secure.information.name" bundle="${content}"/></h6>
                        </label>
                        <div class="col-6 offset-1">
                            <h6>${administrator.getFirstname()}</h6>
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-4 col-form-label">
                            <h6><fmt:message key="secure.information.surname" bundle="${content}"/></h6>
                        </label>
                        <div class="col-6 offset-1">
                            <h6>${administrator.getSurname()}</h6>
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-4 col-form-label">
                            <h6><fmt:message key="secure.information.lastname" bundle="${content}"/></h6>
                        </label>
                        <div class="col-6 offset-1">
                            <h6>${administrator.getLastname()}</h6>
                        </div>
                    </div>
                    <a class="hr row offset-3 col-3 border border-info btn-primary"
                       href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts">
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

