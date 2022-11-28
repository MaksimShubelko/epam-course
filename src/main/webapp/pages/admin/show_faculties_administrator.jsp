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
    <title><fmt:message key="show.faculties.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<form class="w-auto h-75 container" action="${pageContext.request.contextPath}/controller" method="get">
    <div class="container__content pt-5 pb-5">
        <table class="table table-bordered table-responsive-lg">
            <tr class="text-center text-uppercase text-nowrap">
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
                    <td class="text-center">${loop.index + 1 + (currentPage - 1) * 5}</td>
                    <td>${faculty.getFacultyName()}</td>
                    <td class="text-center">${faculty.getRecruitmentPlanFree()}</td>
                    <td class="text-center">${faculty.getRecruitmentPlanCanvas()}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_faculty&faculty_id=${faculty.getFacultyId()}&page=${currentPage}">
                            <fmt:message key="admin.main.page.faculties.edit" bundle="${content}"/>

                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=delete_faculty&faculty_id=${faculty.getFacultyId()}&page=${currentPage}">
                            <fmt:message key="admin.main.page.faculties.delete" bundle="${content}"/>
                        </a>
                    </td>

                </tr>
            </c:forEach>
        </table>
        <nav>
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/controller?command=command=go_to_show_faculties&page=${currentPage - 1}"
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
                                   href="${pageContext.request.contextPath}/controller?command=go_to_show_faculties&page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt countPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/controller?command=command=go_to_show_faculties&page=${currentPage + 1}"
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
</form>
</body>
<jsp:include page="../footer.jsp"/>
</html>
