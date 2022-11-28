<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar_header.css">
</head>
<body class="font-comforta">
<form class="mb-0" action="${pageContext.request.contextPath}/controller" method="get">
    <div class="nav navbar-expand-lg navbar-header navbar-light ps-2">
        <c:if test="${recruitment != null}">
            <div class="row col-8 d-flex flex-wrap pt-2 align-content-center text-center">
                <div class="col-3 m-0 p-0 pt-3">
                    <fmt:message key="applicant.mail.page.recruitment.status" bundle="${content}"/>
                </div>
                <div class="col-1 mb-2 ps-1 p-0 text-center">
                    <c:set var="recruitment_status" value="${recruitment.getRecruitmentStatus()}"/>
                    <c:choose>
                        <c:when test="${recruitment_status == true}">
                            <img src="${pageContext.request.contextPath}/icons/yes_icon.png" alt="recruitment status">
                        </c:when>
                        <c:when test="${recruitment_status == false}">
                            <img src="${pageContext.request.contextPath}/icons/no_icon.png" alt="recruitment status">
                        </c:when>
                    </c:choose>
                </div>
                <div class="col-2 pt-3 p-0 m-0">
                    <fmt:message key="applicant.mail.page.recruitment.finish.time" bundle="${content}"/>
                </div>
                <div class="col-2 pt-3 p-0 m-0">
                        ${recruitment.getFinishRecruitment()}
                </div>
                <c:if test="${message_success != null}">
                    <h6 class="text-success col-3 m-0 p-0 pt-2 mt-1 text-center" id="message">
                        <fmt:message key="${message_success}" bundle="${content}"/>
                    </h6>
                    <div>
                            ${pageContext.request.getSession().removeAttribute("message_success")}
                    </div>
                </c:if>
            </div>
        </c:if>

        <div class="col-1 pt-1 mt-1 offset-sm-1">
            <h6>
                <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_applicant_data">
                    <img src="${pageContext.request.contextPath}/icons/profile_icon.png" alt="<fmt:message key="profile.welcome.message" bundle="${content}"/>"/>
                </a>
            </h6>
        </div>
        <div class="col-1 pt-1 mt-1">
            <h6>
                <a href="${pageContext.request.contextPath}/controller?command=logout">
                    <img src="${pageContext.request.contextPath}/icons/exit_icon.png" alt="<fmt:message key="logout" bundle="${content}"/>"/>
                </a>
            </h6>
        </div>
    </div>
    </div>
</form>
</body>
</html>

