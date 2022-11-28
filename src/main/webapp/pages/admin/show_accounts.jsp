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
    <title><fmt:message key="show.accounts.title" bundle="${content}"/></title>
</head>
<body class="font-comforta">
<form class="w-auto h-75 container" action="${pageContext.request.contextPath}/controller" method="get">
    <div class="container__content pt-5 pb-5">
        <table class="table table-bordered table-responsive-lg">
            <tr class="text-center text-uppercase">
                    <th><fmt:message key="admin.main.page.accounts.number" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.login" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.email" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.role" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.status" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.ip" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.change.status" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.delete" bundle="${content}"/></th>
            </tr>

            <c:forEach var="account" items="${accounts}" varStatus="loop">
                <tr>
                    <td  class="text-center text-uppercase">${loop.index + 1 + (currentPage - 1) * 5}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_profile_page&account_id=${account.getAccountId()}">
                                ${account.getLogin()}
                        </a>
                    </td>
                    <td>${account.getEmail()}</td>
                    <td>${account.getRole()}</td>
                    <td>${account.getStatus()}</td>
                    <td>${account.getIp()}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=change_account_status&account_id=${account.getAccountId()}&accountStatus=${account.getStatus()}&page=${currentPage}">
                            <c:if test="${account.getStatus() == 'ACTIVE'}">
                                <fmt:message key="admin.main.page.account.status.block" bundle="${content}"/>
                            </c:if>
                            <c:if test="${account.getStatus() == 'BLOCKED'}">
                                <fmt:message key="admin.main.page.account.status.unblock" bundle="${content}"/>
                            </c:if>

                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=delete_account&account_id=${account.getAccountId()}&page=${currentPage}">
                            delete
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
                               href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts&page=${currentPage - 1}"
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
                                       href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts&page=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt countPages}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts&page=${currentPage + 1}"
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
    </div>
</form>
</body>
<jsp:include page="../footer.jsp"/>
</html>
