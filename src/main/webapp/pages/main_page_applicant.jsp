<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/button.css" type="text/css" rel="stylesheet">
    <jsp:include page="header.jsp"/>
    <title>Insert title here</title>
</head>
<body>
<form name="applicantPage" action="${pageContext.request.contextPath}/controller" method="get" class="container h-75">
    <input type="hidden" name="command" value="show_requests_command">
    <div class="row">
        <div class="col-2 offset-sm-9">
            <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_applicant_data">
                <fmt:message key="applicant.edit.data" bundle="${content}"/>
            </a>
        </div>
        <div class="col-1">
            <a href="${pageContext.request.contextPath}/controller?command=logout">
                <fmt:message key="logout" bundle="${content}"/>
            </a>
        </div>
    </div>
    <div>
        <div class="row">
            <div class="row-cols-auto">
                <div class="col-6">
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_add_request_page">
                        <fmt:message key="applicant.page.add.request" bundle="${content}"/>
                    </a>
                </div>
            </div>
            <div class="col-5 bg-body">
                <c:set var="faculty_id"/>
                <c:forEach var="faculty" items="${faculties}" varStatus="loop">
                    <button type="submit" name="faculty_id" value="${faculty.getFacultyId()}"
                            class="btn btn-link ui-corner-left">${loop.index + 1}. ${faculty.getFacultyName()}</button>
                </c:forEach>
            </div>

            <c:if test="${countPages > 0}">
                <div class="col-7 container__content">
                    <table class="table table-bordered table-sm">
                        <tr>
                            <th><fmt:message key="applicant.page.table.number" bundle="${content}"/></th>
                            <th><fmt:message key="applicant.page.table.firstname" bundle="${content}"/></th>
                            <th><fmt:message key="applicant.page.table.surname" bundle="${content}"/></th>
                            <th><fmt:message key="applicant.page.table.lastname" bundle="${content}"/></th>
                            <th><fmt:message key="applicant.page.table.benefits" bundle="${content}"/></th>
                            <th><fmt:message key="applicant.page.table.recruitment.status" bundle="${content}"/></th>
                        </tr>

                        <c:forEach var="applicant" items="${applicants}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1 + (currentPage - 1) * 5}</td>
                                <td>${applicant.getFirstname()}</td>
                                <td>${applicant.getSurname()}</td>
                                <td>${applicant.getLastname()}</td>
                                <c:choose>
                                    <c:when test="${applicant.getBeneficiary()}">
                                        <td><fmt:message key="applicant.page.table.benefits.exist" bundle="${content}"/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><fmt:message key="applicant.page.table.benefits.not.exist" bundle="${content}"/></td>
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
                                    <td><fmt:message key="applicant.page.table.recruitment.status.not.receive"
                                                     bundle="${content}"/></td>
                                </c:if>

                            </tr>
                        </c:forEach>
                    </table>

                    <div class="row w-75">
                        <div class="col-3">
                            <c:if test="${currentPage != 1}">
                                <td>
                                    <a href="${pageContext.request.contextPath}/controller?command=show_requests_command&page=${currentPage - 1}"><fmt:message
                                            key="pagination.previous.page" bundle="${content}"/></a>
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
                                                    <a href="${pageContext.request.contextPath}/controller?command=show_requests_command&page=${i}">${i}</a>
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
                                    <a href="${pageContext.request.contextPath}/controller?command=show_requests_command&page=${currentPage + 1}">
                                        <fmt:message key="pagination.next.page" bundle="${content}"/></a>
                                </td>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    </div>
</form>
</body>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</html>
