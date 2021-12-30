<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="local.pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<!DOCTYPE html>
<html>
<head>
    <title>Insert title here</title>
</head>
<body>
<div align="center">
    <h1>Employee Login Form</h1>
    <form name="loginForm" action="controller" method="get">
        <input type="hidden" name="command" value="create_account"/>
        <table style="with: 100%">
            <tr>
                <td>Login</td>
                <td><input type="text" name="login" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>

        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>