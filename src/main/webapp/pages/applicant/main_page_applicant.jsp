<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <jsp:include page="../header.jsp"/>
    <jsp:include page="/pages/applicant/navbar_header.jsp"/>
    <title><fmt:message key="applicant.main.page.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<form name="applicantPage" action="${pageContext.request.contextPath}/controller" method="get"
      class="h-100">
    <input type="hidden" name="command" value="show_requests_command">
    <div>
        <div class="p-2">
            <div>
                <div class="row">
                    <div class="align-content-center row">
                        <div class="justify-content-center p-0 m-0">
                            <a class="btn btn-sm rounded-2 ps-1 col-2 row"
                               href="${pageContext.request.contextPath}/controller?command=go_to_add_request_page">
                                <fmt:message key="applicant.page.add.request" bundle="${content}"/>
                            </a>
                        </div>
                        <div class=" justify-content-center border-success p-1 ps-1">
                            <a class="btn btn-sm rounded-2 ps-1 col-2 row"
                               href="${pageContext.request.contextPath}/controller?command=pick_up_documents">
                                <fmt:message key="applicant.page.pick.up.documents" bundle="${content}"/>
                            </a>
                        </div>
                        <c:if test="${message != null}">
                            <label class="text-danger text-sm-start col-2 ps-3 ms-3">
                                <h6>
                                    <fmt:message key="${message}" bundle="${content}"/>
                                </h6>
                            </label>
                        </c:if>
                        <div>
                            ${pageContext.request.getSession().removeAttribute("message")}
                        </div>
                    </div>
                    <div class="col-5 bg-body">
                        <c:set var="faculty_id"/>
                        <c:forEach var="faculty" items="${faculties}" varStatus="loop">
                            <br>
                            <button type="submit" name="faculty_id" value="${faculty.getFacultyId()}"
                                    class="btn text-success btn-link ui-corner-left">${loop.index + 1}. ${faculty.getFacultyName()}</button>
                        </c:forEach>
                    </div>
                    <div class="col-7">
                        <c:if test="${countPages > 0}">
                            <div class="container__content">
                                <table class="table table-bordered table-responsive-lg">
                                    <tr>
                                        <th><fmt:message key="applicant.page.table.number" bundle="${content}"/></th>
                                        <th><fmt:message key="applicant.page.table.firstname" bundle="${content}"/></th>
                                        <th><fmt:message key="applicant.page.table.surname" bundle="${content}"/></th>
                                        <th><fmt:message key="applicant.page.table.lastname" bundle="${content}"/></th>
                                        <th><fmt:message key="applicant.page.table.benefits" bundle="${content}"/></th>
                                        <th><fmt:message key="applicant.page.table.recruitment.status"
                                                         bundle="${content}"/></th>
                                    </tr>

                                    <c:forEach var="applicant" items="${applicants}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1 + (currentPage - 1) * 5}</td>
                                            <td>${applicant.getFirstname()}</td>
                                            <td>${applicant.getSurname()}</td>
                                            <td>${applicant.getLastname()}</td>
                                            <c:choose>
                                                <c:when test="${applicant.getBeneficiary()}">
                                                    <td><fmt:message key="applicant.page.table.benefits.exist"
                                                                     bundle="${content}"/></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><fmt:message key="applicant.page.table.benefits.not.exist"
                                                                     bundle="${content}"/></td>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${(loop.index + 1 + (currentPage - 1) * 5) <= faculty.getRecruitmentPlanFree()}">
                                                <td><fmt:message key="applicant.page.table.recruitment.status.free"
                                                                 bundle="${content}"/></td>
                                            </c:if>
                                            <c:if test="${faculty.getRecruitmentPlanFree() < (loop.index + 1 + (currentPage - 1) * 5) && (loop.index + 1 + (currentPage - 1) * 5) <= (faculty.getRecruitmentPlanCanvas() + faculty.getRecruitmentPlanFree())}">
                                                <td><fmt:message key="applicant.page.table.recruitment.status.canvas"
                                                                 bundle="${content}"/></td>
                                            </c:if>
                                            <c:if test="${(loop.index + 1 + (currentPage - 1) * 5) > (faculty.getRecruitmentPlanCanvas() + faculty.getRecruitmentPlanFree())}">
                                                <td><fmt:message
                                                        key="applicant.page.table.recruitment.status.not.receive"
                                                        bundle="${content}"/></td>
                                            </c:if>

                                        </tr>
                                    </c:forEach>
                                </table>
                                <nav>
                                    <ul class="pagination">
                                        <c:if test="${currentPage != 1}">
                                            <li class="page-item">
                                                <a class="page-link"
                                                   href="${pageContext.request.contextPath}/controller?command=show_requests_command&page=${currentPage - 1}"
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
                                                           href="${pageContext.request.contextPath}/controller?command=show_requests_command&page=${i}">${i}</a>
                                                    </li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${currentPage lt countPages}">
                                            <li class="page-item">
                                                <a class="page-link"
                                                   href="${pageContext.request.contextPath}/controller?command=show_requests_command&page=${currentPage + 1}"
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
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
<footer>
    <jsp:include page="../footer.jsp"/>
</footer>
</html>
