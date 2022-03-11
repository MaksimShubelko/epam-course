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
    <title><fmt:message key="show.accounts.title" bundle="${content}"/></title>
</head>
<body>
<form name="applicantPage" action="${pageContext.request.contextPath}/controller" method="get" class="h-75">
    <div class="col-7 container__content">
        <table class="table table-bordered table-sm">
            <tr>
                <h6>
                    <th><fmt:message key="admin.main.page.accounts.number" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.login" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.email" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.role" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.status" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.ip" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.change.status" bundle="${content}"/></th>
                    <th><fmt:message key="admin.main.page.accounts.delete" bundle="${content}"/></th>
                </h6>
            </tr>

            <c:forEach var="account" items="${accounts}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1 + (currentPage - 1) * 5}</td>
                    <td><a href="${pageContext.request.contextPath}/controller?command=go_to_profile_page&account_id=${account.getAccountId()}">
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

        <div class="row w-75">
            <div class="col-3">
                <c:if test="${currentPage != 1}">
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts&page=${currentPage - 1}">
                            <fmt:message key="pagination.previous.page" bundle="${content}"/></a>
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
                                        <a href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts&page=${i}">${i}</a>
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
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_show_accounts&page=${currentPage + 1}">
                            <fmt:message key="pagination.next.page" bundle="${content}"/>
                        </a>
                    </td>
                </c:if>
            </div>
        </div>
    </div>
</form>
</body>
<jsp:include page="../footer.jsp"/>
</html>
