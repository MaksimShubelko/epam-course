<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="css/button.css" type="text/css" rel="stylesheet">
    <title>Insert title here</title>
</head>
<body>
<form class="needs-validation h-100" novalidate action="${pageContext.request.contextPath}/controller" method="get">
    <div class="row">
        <div class="col-2 offset-sm-9">
            <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_administrator_data">
                <fmt:message key="administrator.edit.data" bundle="${content}"/>
            </a>
        </div>
        <div class="col-1">
            <a href="${pageContext.request.contextPath}/controller?command=logout">
                <fmt:message key="logout" bundle="${content}"/>
            </a>
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
                                    <fmt:message key="admin.main.page.accounts" bundle="${content}"/>
                                </a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=go_to_add_admin_account&page=1">
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

    <div class="row">
        <div class="col-6 p-5 offset-sm-2">
            <input type="hidden" name="command" value="send_message">
            <input type="hidden" name="email" value="${email}">
            <c:set var="title" value="${title}"/>
            <c:set var="message" value="${message}"/>

            <div class="row">
                <fmt:message key="admin.sender.page.send.to" bundle="${content}"/>
                <div class="col-3">
                    <h6>${email}</h6>
                </div>
            </div>

            <div class="row">
                <input type="text" class="form-control h-25 m-3"
                       placeholder="<fmt:message key="admin.sender.page.title" bundle="${content}"/>" name="title"
                       value="${title}" aria-placeholder=""
                       aria-describedby="basic-addon1">
                <textarea type="text" class="form-control h-50 m-3"
                          placeholder="<fmt:message key="admin.sender.page.message" bundle="${content}"/>"
                          name="message"
                          aria-describedby="basic-addon1">${message}</textarea>

            </div>
            <div class="row">
                <div class="col-3">
                    <input type="submit" name="submit">
                </div>
                <div class="col-3">
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_show_applicants&page=1">
                        <fmt:message key="btn.back" bundle="${content}"/></a>
                </div>
            </div>

        </div>
    </div>
</form>
</body>
<jsp:include page="footer.jsp"/>
</html>
