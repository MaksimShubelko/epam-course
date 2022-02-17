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
    <title><fmt:message key="admin.main.page.title" bundle="${content}"/></title>
</head>
<body>
<form class="h-75" action="${pageContext.request.contextPath}/controller" method="get">
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
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <div class="d-flex justify-content-between">
                    <div class="input-group p-1">
                        <div class="input-group-prepend">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                <fmt:message key="admin.main.page.faculties" bundle="${content}"/>
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=go_to_show_faculties&page=1">
                                    <fmt:message key="admin.main.page.faculties.show" bundle="${content}"/>
                                </a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=go_to_add_faculty_page">
                                    <fmt:message key="admin.main.page.faculties.add" bundle="${content}"/>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="input-group p-1">
                        <div class="input-group-prepend">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                <fmt:message key="admin.main.page.accounts" bundle="${content}"/>
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts&page=1">
                                    <fmt:message key="admin.main.page.accounts.show" bundle="${content}"/>
                                </a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=go_to_add_admin_account">
                                    <fmt:message key="admin.main.page.accounts.add.admin" bundle="${content}"/>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="input-group p-1">
                        <div class="input-group-prepend">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                <fmt:message key="admin.main.page.applicants" bundle="${content}"/>
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=go_to_show_applicants&page=1">
                                    <fmt:message key="admin.main.page.applicants.search" bundle="${content}"/>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="input-group p-1">
                        <div class="input-group-prepend">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">Dropdown
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=go_to_edit_recruitment_page">
                                    <fmt:message key="admin.main.page.recruitment.update" bundle="${content}"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </ul>
        </div>
    </nav>
</form>
</body>
<jsp:include page="../footer.jsp"/>
</html>
