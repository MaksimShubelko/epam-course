<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="mb-0" action="${pageContext.request.contextPath}/controller" method="get">
    <div class="nav navbar-expand-lg navbar-light bg-warning">
        <div class="col-2">
            <fmt:message key="applicant.mail.page.recruitment.status" bundle="${content}"/>
        </div>
        <div class="col-2">
            ${recruitment.getRecruitmentStatus()}
        </div>
        <div class="col-2">
            <fmt:message key="applicant.mail.page.recruitment.finish.time" bundle="${content}"/>
        </div>
        <div class="col-2">
            ${recruitment.getFinishRecruitment()}
        </div>

        <div class="col-1 offset-1 border border-success border-3">
            <h6>
                <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_applicant_data">
                    <fmt:message key="applicant.edit.data" bundle="${content}"/>
                </a>
            </h6>
        </div>
        <div class="col-1 border border-success border-3">
            <h6>
                <a href="${pageContext.request.contextPath}/controller?command=logout">
                    <fmt:message key="logout" bundle="${content}"/>
                </a>
            </h6>
        </div>
    </div>
</form>
</body>
</html>
