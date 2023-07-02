<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <style>
        <%@include file='styles/employee_info.css' %>
    </style>
</head>
<body>
    <div align="center">
        <h2>You are logged in</h2>
        <img class="round" src="${pageContext.request.contextPath}/views/img/avatar.jpg" alt="photo">
        <div align="center" class="user-info">
            <p><b>Your name: </b> ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>
            <p><b>Your username: </b>${sessionScope.user.username}</p>
            <p><b>Your password: </b> ${sessionScope.user.password}</p>
            <p><b>Your address: </b>${sessionScope.user.address}</p>
            <p><b>Your contact: </b>${sessionScope.user.contact}</p>
        </div>
    </div>
</body>
</html>
