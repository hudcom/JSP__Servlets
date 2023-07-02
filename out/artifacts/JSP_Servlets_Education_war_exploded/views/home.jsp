<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file='styles/home.css' %>
    </style>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div align="center">
        <h1>Test JSP + JavaServlets project</h1>
    </div>
    <div class="info">
        <img class="round" src="${pageContext.request.contextPath}/views/img/photo.jpg" alt="photo">
        <p class="text">This site is a pet-project of a developer called hudcom. (<a href="https://github.com/hudcom">github</a>).<br>
            This site was created to consolidate my knowledge of JSP and Java Servlets. Tomcat and MySql Workbench are used</p>
    </div>

    <hr>

    <%if (session.getAttribute("user") != null){%>
        <jsp:include page="employee_info.jsp" />
    <%} else {%> <p align="center">You are not logged in</p><%}%>
</body>
</html>