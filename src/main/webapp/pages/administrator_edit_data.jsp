<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.content" var="content"/>

<html>
<head>
  <jsp:include page="header.jsp"/>
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/button.css" type="text/css" rel="stylesheet">
  <title>Insert title here</title>
</head>
<body>
<div>
  <form class="container needs-validation h-75" novalidate action="${pageContext.request.contextPath}/controller"
        method="get">
    <input type="hidden" name="command" value="update_administrator_data"/>
    <div class="offset-4 p-5 w-75">
      <div class="offset-3">
        <h6><fmt:message key="edit.administrator.data.welcome.message" bundle="${content}"/></h6>
      </div>

      <div class="row">
        <label class="col-3 col-form-label">
          <h6><fmt:message key="secure.information.name" bundle="${content}"/></h6>
        </label>
        <div class="col-6">
          <input type="text" name="name" pattern="[А-ЯЁ][а-яё]+" value="${administrator.getFirstname()}"
                 required/>
          <div class="invalid-feedback">
            <h6><fmt:message key="name.secure.information.format.error" bundle="${content}"/></h6>
          </div>
        </div>
      </div>

      <div class="row">
        <label class="col-3 col-form-label">
          <h6><fmt:message key="secure.information.surname" bundle="${content}"/></h6>
        </label>
        <div class="col-6">
          <input type="text" name="surname"
                 pattern="[А-ЯЁ][а-яё]+([-][А-ЯЁ][а-яё]+)?" value="${administrator.getSurname()}" required>
          <div class="invalid-feedback">
            <h6><fmt:message key="surname.secure.information.format.error" bundle="${content}"/></h6>
          </div>
        </div>
      </div>

      <div class="row">
        <label class="col-3 col-form-label">
          <h6><fmt:message key="secure.information.lastname" bundle="${content}"/></h6>
        </label>
        <div class="col-6">
          <input type="text" name="lastname" pattern="[А-ЯЁ][а-яё]+" value="${administrator.getLastname()}"
                 required>
          <div class="invalid-feedback">
            <h6><fmt:message key="lastname.secure.information.format.error" bundle="${content}"/></h6>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-10 offset-4 p-1">
          <input type="submit"
                 value="<fmt:message key="edit.administrator.data.button" bundle="${content}" />"
                 class="blubtn align-middle"/>
        </div>
      </div>
    </div>
  </form>
</div>
<script src="js/validation.js"></script>
</body>
<footer>
  <jsp:include page="footer.jsp"/>
</footer>
</html>

