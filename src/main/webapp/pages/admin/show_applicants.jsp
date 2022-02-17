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
    <title><fmt:message key="show.applicants.title" bundle="${content}"/></title>
</head>
<body>
<form class="needs-validation h-75" novalidate action="${pageContext.request.contextPath}/controller" method="get"
      class="h-75">
    <input type="hidden" name="command" value="go_to_show_applicants">
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
    <div class="d-flex justify-content-start p-3 w-100">
        <div class="custom-control custom-radio p-3 col-1">
            <input type="radio" id="archive" value="archive" name="recruitment_status" class="custom-control-input">
            <label class="custom-control-label" for="archive">
                <fmt:message key="admin.main.page.applicants.archive" bundle="${content}"/>
            </label>
        </div>
        <div class="custom-control custom-radio p-3 col-1">
            <input type="radio" id="all" value="all" name="recruitment_status" class="custom-control-input" checked>
            <label class="custom-control-label" for="all">
                <fmt:message key="admin.main.page.applicants.all" bundle="${content}"/>
            </label>
        </div>
        <div class="custom-control custom-radio p-3 col-1">
            <input type="radio" id="free" value="free" name="recruitment_status" class="custom-control-input">
            <label class="custom-control-label" for="free">
                <fmt:message key="admin.main.page.applicants.free" bundle="${content}"/>
            </label>
        </div>
        <div class="custom-control custom-radio p-3 col-1">
            <input type="radio" id="canvas" value="canvas" name="recruitment_status" class="custom-control-input">
            <label class="custom-control-label" for="canvas">
                <fmt:message key="admin.main.page.applicants.canvas" bundle="${content}"/>
            </label>
        </div>
        <div class="custom-control custom-radio p-3 col-1">
            <input type="radio" id="not_received" value="not_received" name="recruitment_status"
                   class="custom-control-input">
            <label class="custom-control-label" for="not_received">
                <fmt:message key="admin.main.page.applicants.not.received" bundle="${content}"/>
            </label>
        </div>
        <div class="col-5 p-3 offset-1">
            <select class="form-select" name="faculty_id" required>
                <c:forEach var="faculty" items="${faculties}" varStatus="loop">
                    <option value="${faculty.getFacultyId()}">${faculty.getFacultyName()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="input-group h-25 w-25 p-3 col-1">
            <div class="input-group-append">
                <input class="btn btn-success"
                       value="<fmt:message key="admin.main.page.applicants.search" bundle="${content}"/>" type="submit">
            </div>
        </div>
    </div>
    </div>

    <c:if test="${countPages > 0}">

        <div class="col-8 pb-1">
            <table class="table table-bordered table-sm">
                <tr>
                    <th><fmt:message key="admin.main.page.applicants.number" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.login" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.email" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.certificate" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.math" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.physic" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.english" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.firstname" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.surname" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.lastname" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.applicants.send.message" bundle="${content}"/></th>
                </tr>

                <c:forEach var="applicant" items="${applicants}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1 + (currentPage - 1) * 5}</td>
                        <c:if test="${accounts.get(loop.index).getAccountId() == applicant.getAccountId()}">
                            <td>${accounts.get(loop.index).getLogin()}</td>
                            <td>${accounts.get(loop.index).getEmail()}</td>
                            <td>${certificates.get(loop.index).getTotalMark()}</td>
                        </c:if>
                        <c:forEach var="subject" items="${subjects.get(loop.index)}" varStatus="loop">
                            <td>${subject.getMark()}</td>
                        </c:forEach>
                        <td>${applicant.getFirstname()}</td>
                        <td>${applicant.getSurname()}</td>
                        <td>${applicant.getLastname()}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_sender_page&email=${accounts.get(loop.index).getEmail()}&page=${currentPage}">
                                Send message
                            </a>
                        </td>

                    </tr>
                </c:forEach>
            </table>
            <div class="offset-sm-1">
                <div class="row w-75">
                    <div class="col-3">
                        <c:if test="${currentPage != 1}">
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_show_applicants&page=${currentPage - 1}&faculty_id=${faculty_id}">
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
                                                <a href="${pageContext.request.contextPath}/controller?command=go_to_show_applicants&page=${i}&faculty_id=${faculty_id}">${i}</a>
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
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_show_applicants&page=${currentPage + 1}&faculty_id=${faculty_id}">
                                    <fmt:message key="pagination.next.page" bundle="${content}"/></a>
                                </a>
                            </td>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</form>
<script src="${pageContext.request.contextPath}/js/select_save_selected.js"></script>
<script src="${pageContext.request.contextPath}/js/radio_btn_save_checked.js"></script>
</body>
<jsp:include page="../footer.jsp"/>
</html>
