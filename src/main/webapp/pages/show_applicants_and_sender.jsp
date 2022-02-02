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
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <input type="hidden" name="command" value="send_message">
        <input type="hidden" name="email" value="${email}">
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
                                   href="${pageContext.request.contextPath}/controller?command=show_faculties&page=1">
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
                                   href="${pageContext.request.contextPath}/controller?command=show_accounts&page=1">Show</a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=go_to_add_admin_account&page=1">Add
                                    admin account</a>
                            </div>
                        </div>
                    </div>

                    <div class="input-group p-1">
                        <div class="input-group-prepend">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">Dropdown
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                            </div>
                        </div>
                    </div>

                    <div class="input-group p-1">
                        <div class="input-group-prepend">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">Dropdown
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                            </div>
                        </div>
                    </div>

                    <div class="input-group p-1">
                        <div class="input-group-prepend">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">Dropdown
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                            </div>
                        </div>
                    </div>
                </div>
            </ul>
        </div>
    </nav>

    <div class="row">
        <div class="col-6 p-5 offset-sm-2">
            <c:set var="title" value="${title}"/>
            <c:set var="message" value="${message}"/>
            <input type="text" class="form-control h-25 m-3" placeholder="" aria-label="" name="title" value="${title}"
                   aria-describedby="basic-addon1">
            <textarea type="text" class="form-control h-50 m-3" placeholder="" aria-label="" name="message"
                      aria-describedby="basic-addon1">${message}</textarea>
            <input type="submit" name="submit">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=go_to_show_applicants_page&page=1">
                Show applicants</a>

        </div>
    </div>
</form>
</body>
<jsp:include page="footer.jsp"/>
</html>
