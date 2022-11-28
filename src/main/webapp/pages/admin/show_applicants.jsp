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
    <title><fmt:message key="show.applicants.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<form class="h-75 container" action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="go_to_show_applicants">
    <div class="d-flex row justify-content-center rounded-1 pt-3 pb-3 ps-5 ms-2 me-2 border border-5 border-dark">
        <div class="col-4">
            <div class="custom-radio p-1">
                <input type="radio" id="archive" value="archive" name="recruitment_status">
                <fmt:message key="admin.main.page.applicants.archive" bundle="${content}"/>
            </div>
            <div class="custom-radio p-1">
                <input type="radio" id="all" value="all" name="recruitment_status">
                <fmt:message key="admin.main.page.applicants.all" bundle="${content}"/>
            </div>
            <div class="custom-radio p-1">
                <input type="radio" id="free" value="free" name="recruitment_status">
                <fmt:message key="admin.main.page.applicants.free" bundle="${content}"/>
            </div>
            <div class="custom-radio p-1">
                <input type="radio" id="canvas" value="canvas" name="recruitment_status">
                <fmt:message key="admin.main.page.applicants.canvas" bundle="${content}"/>
            </div>
            <div class="custom-radio p-1">
                <input type="radio" id="not_received" value="not_received" name="recruitment_status">
                <fmt:message key="admin.main.page.applicants.not.received" bundle="${content}"/>
            </div>
        </div>
        <div class="col-5 pt-4 mt-2">
            <select class="form-select" name="faculty_id" required>
                <c:forEach var="faculty" items="${faculties}" varStatus="loop">
                    <option value="${faculty.getFacultyId()}">${faculty.getFacultyName()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="input-group h-25 w-25 pt-4 mt-2 col-1">
            <div class="input-group-append">
                <input class="btn btn-success"
                       value="<fmt:message key="admin.main.page.applicants.search" bundle="${content}"/>" type="submit">
            </div>
        </div>
    </div>

    <c:if test="${countPages > 0}">
        <div class="container__content pt-2">
            <table class="table table-bordered table-responsive-lg">
                <tr class="text-center text-uppercase col-3 text-nowrap">
                    <th class="text-center"><fmt:message key="admin.main.page.applicants.number" bundle="${content}"/></th>
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
                        <td class="text-center">${loop.index + 1 + (currentPage - 1) * 5}</td>
                        <c:if test="${accounts.get(loop.index).getAccountId() == applicant.getAccountId()}">
                            <td>${accounts.get(loop.index).getLogin()}</td>
                            <td>${accounts.get(loop.index).getEmail()}</td>
                            <td class="text-center">${certificates.get(loop.index).getTotalMark()}</td>
                        </c:if>
                        <c:forEach var="subject" items="${subjects.get(loop.index)}" varStatus="loop">
                            <td class="text-center">${subject.getMark()}</td>
                        </c:forEach>
                        <td>${applicant.getFirstname()}</td>
                        <td>${applicant.getSurname()}</td>
                        <td>${applicant.getLastname()}</td>
                        <td>
                            <a class="text-nowrap" href="${pageContext.request.contextPath}/controller?command=go_to_sender_page&email=${accounts.get(loop.index).getEmail()}&page=${currentPage}">
                                <fmt:message key="admin.main.page.applicants.messaging" bundle="${content}"/>
                            </a>
                        </td>

                    </tr>
                </c:forEach>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/controller?command=command=go_to_show_applicants&page=${currentPage - 1}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only"><fmt:message key="pagination.previous.page"
                                                                   bundle="${content}"/></span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${countPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item">
                                    <a class="page-link">${i}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/controller?command=command=go_to_show_applicants&page=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt countPages}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/controller?command=command=go_to_show_applicants&page=${currentPage + 1}"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only"><fmt:message key="pagination.next.page"
                                                                   bundle="${content}"/></span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </c:if>
</form>
<script src="${pageContext.request.contextPath}/js/select_save_selected.js"></script>
<script src="${pageContext.request.contextPath}/js/radio_btn_save_checked.js"></script>
</body>
<jsp:include page="../footer.jsp"/>
</html>
