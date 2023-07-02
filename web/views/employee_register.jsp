<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register employee</title>
    <style>
        <%@include file='styles/employee_register.css' %>
    </style>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div align="center">
        <h1>Employee register form</h1>
        <form action="<%request.getContextPath();%>/register" method="post">
            <table>
                <!-- First name -->
                <tr>
                    <td>First name</td>
                    <td><input type="text" name="firstname" id="text1" pattern=".{1,}" required></td>
                </tr>
                <!-- Last name -->
                <tr>
                    <td>Last name</td>
                    <td><input type="text" name="lastname" id="text2" pattern=".{1,}" required></td>
                </tr>
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
                <!-- Address -->
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="address" pattern=".{1,}" required></td>
                </tr>
                <!-- Contact -->
                <tr>
                    <td>Contact</td>
                    <td><input type="text" name="contact" pattern=".{1,}" required></td>
                </tr>
            </table>
            <input type="submit" value="Register" id="btn"></form>
        <form method="get" action="<%request.getContextPath();%>/home">
            <br><button>Back to main page</button>
        </form>

    </div>
</body>
</html>
