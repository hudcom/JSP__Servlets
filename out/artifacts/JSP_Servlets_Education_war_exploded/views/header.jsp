<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file='styles/header.css' %>
    </style>
</head>
<body>
    <div  class="nav" align="right">
        <form class="header-form" method="get" action="<%request.getContextPath();%>/home">
            <button>Home</button>
        </form>
        <form class="header-form" method="get" action="<%request.getContextPath();%>/user-list">
            <button>Users</button>
        </form>
        <form class="header-form" method="get" action="<%request.getContextPath();%>/register">
            <button>Register</button>
        </form>
        <form class="header-form" method="get" action="<%request.getContextPath();%>/login">
            <button>Login</button>
        </form>

        <hr class="header">
    </div>
</body>
</html>
