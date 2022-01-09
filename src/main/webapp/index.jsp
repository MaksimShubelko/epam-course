<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="local.pagecontent"/>
<html>
    <head>
        <title>Index Page</title>
    </head>
    <body>
        <c:redirect url="/controller?command=go_to_login_page"/>
    </body>
</html>