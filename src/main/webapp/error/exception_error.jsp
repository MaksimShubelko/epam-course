<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><fmt:message key="error.title" bundle="${content}"/></title>
</head>

<body>

<div class="container">
    <h1><fmt:message key="error.oops" bundle="${content}"/></h1>

    <table class="table table-bordered">
        <tbody>
        <tr>
            <th><fmt:message key="error.request"/></th>
            <td>${pageContext.errorData.requestURI}</td>
        </tr>
        <tr>
            <th><fmt:message key="error.servlet"/></th>
            <td>${pageContext.errorData.servletName}</td>
        </tr>
        <tr>
            <th><fmt:message key="error.exception"/></th>
            <td>${pageContext.errorData.throwable}</td>
        </tr>
        <tr>
            <th><fmt:message key="error.message"/></th>
            <td>${pageContext.exception.message}</td>
        </tr>
        <tr>
            <th><fmt:message key="error.status_code"/></th>
            <td>${pageContext.errorData.statusCode}</td>
        </tr>
        <tr>
            <th><fmt:message key="error.stack_trace"/></th>
            <td>
                <c:forEach items="${pageContext.exception.stackTrace}" var="element">
                    element =   ${element}
                    <br/>
                </c:forEach>
            </td>
        </tr>
        </tbody>
    </table>

    <h4>
        <a href="${pageContext.request.contextPath}/controller?command=to_main_page&actual_page=1" class="link-primary">
            <fmt:message key="error.back" bundle="${content}"/>
        </a>
    </h4>
</div>
</div>
</body>
</html>