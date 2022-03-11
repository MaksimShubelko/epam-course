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
    <link href="${pageContext.request.contextPath}/css/button.css" type="text/css" rel="stylesheet">
    <title><fmt:message key="show.faculties.title" bundle="${content}"/></title>
</head>
<body>
<form class="needs-validation h-100" novalidate action="${pageContext.request.contextPath}/controller" method="get">
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
<jsp:include page="../footer.jsp"/>
</html>
