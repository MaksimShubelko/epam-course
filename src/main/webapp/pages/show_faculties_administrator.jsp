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
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=go_toshow_faculties&page=1">
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
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=go_to_show_applicants&page=1">
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
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=go_to_edit_recruitment_page">
                                    <fmt:message key="admin.main.page.recruitment.update" bundle="${content}"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </ul>
        </div>
    </nav>


    <div class="col-7 container__content">
        <table class="table table-bordered table-sm">
            <tr>
                <th>
                    <fmt:message key="admin.main.page.faculties.number" bundle="${content}"/>
                </th>
                <th>
                    <fmt:message key="admin.main.page.faculties.name" bundle="${content}"/>
                </th>
                <th>
                    <fmt:message key="admin.main.page.faculties.recruitment.plan.free" bundle="${content}"/>
                </th>
                <th>
                    <fmt:message key="admin.main.page.faculties.recruitment.plan.canvas" bundle="${content}"/>
                </th>
                <th>
                    <fmt:message key="admin.main.page.faculties.edit" bundle="${content}"/>
                </th>
                <th>
                    <fmt:message key="admin.main.page.faculties.delete" bundle="${content}"/>
                </th>
            </tr>

            <c:forEach var="faculty" items="${faculties}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1 + (currentPage - 1) * 5}</td>
                    <td>${faculty.getFacultyName()}</td>
                    <td>${faculty.getRecruitmentPlanFree()}</td>
                    <td>${faculty.getRecruitmentPlanCanvas()}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_faculty&faculty_id=${faculty.getFacultyId()}">
                            <fmt:message key="admin.main.page.faculties.edit" bundle="${content}"/>

                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=delete_faculty&faculty_id=${faculty.getFacultyId()}">
                            <fmt:message key="admin.main.page.faculties.delete" bundle="${content}"/>
                        </a>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <div class="row w-75">
            <div class="col-3">
                <c:if test="${currentPage != 1}">
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_show_faculties&page=${currentPage - 1}">
                            <fmt:message key="pagination.previous.page" bundle="${content}"/>
                        </a>
                    </td>
                </c:if>
            </div>

            <div class="col-6">
                <table border="1" cellpadding="5" cellspacing="5">
                    <tr>
                        <c:forEach begin="1" end="${countPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/controller?command=go_to_show_faculties&page=${i}">${i}</a>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>
            </div>
            <div class="col-3">
                <c:if test="${currentPage lt countPages}">
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_show_faculties&page=${currentPage + 1}">
                            <fmt:message key="pagination.next.page" bundle="${content}"/></a>
                    </td>
                </c:if>
            </div>
        </div>
    </div>
</form>
</body>
<jsp:include page="footer.jsp"/>
</html>
