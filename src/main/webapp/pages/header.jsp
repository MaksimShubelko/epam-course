<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<link href="${pageContext.request.contextPath}/css/header.css" type="text/css" rel="stylesheet">
<header class="header">
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <div>
            <input type="hidden" name="command" value="locale_command">
            <button name="locale" value="ru_RU" type="submit">RUS</button>
            <button name="locale" value="en_US" type="submit">ENG</button>
        </div>

        <div class="text-box">
            <h1 class="heading-primary">
            <span class="heading-primary-main">
                <fmt:message key="title" bundle="${content}"/>
            </span>
            </h1>
            <%--<a href="#" class="btn btn-white btn-animated">Discover our tours</a>--%>
        </div>
    </form>
</header>
