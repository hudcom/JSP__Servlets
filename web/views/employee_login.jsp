<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file='styles/employee_login.css' %>
    </style>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div align="center">
        <form action="<%request.getContextPath();%>/login" method="post">
            <h1>Employee login form</h1>
            <table>
                <!-- Username -->
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" id="text3" pattern=".{1,}" required></td>
                </tr>
                <!-- Password -->
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" pattern=".{1,}" required></td>
                </tr>
            </table>
            <input type="submit" value="Login" id="btn">
        </form>
    </div>
</body>
</html>
