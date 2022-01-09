<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shube
  Date: 01.01.2022
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="css/button.css" type="text/css" rel="stylesheet">
    <jsp:include page="header.jsp"/>
    <title>Insert title here</title>
</head>
<body>
<div class="container">
    <div class="row">

    </div>
    <div class="row">
        <div class="col-sm-5 bg-light">
            <c:forEach var="faculty" items="${faculties}" varStatus="loop">
                <nav class="nav flex-column">
                    <a class="nav-link active" href="#!">${loop.index + 1}. ${faculty.getFacultyName()}</a>
                </nav>
            </c:forEach>
        </div>

        <div class="col-1">

        </div>
    </div>

    <div class="row">
        <div class="col-2"></div>
        <div class="col-9"></div>
    </div>


</div>

</body>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</html>
