<%@ page import="app.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.EmployeeDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <style>
        <%@include file='styles/user_list.css' %>
    </style>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div align="center">
        <h1 class="user-list-h1">User List</h1>
        <table class="table">
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Username</th>
                <th>Address</th>
                <th>Contact</th>
            </tr>
            <%
                EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
                List<Employee> list = employeeDAO.getUsersList();

                for (Employee emp : list){
                    out.println("<tr>"
                            + "<td>" + emp.getFirstName() + "</td>"
                            + "<td>" + emp.getLastName() + "</td>"
                            + "<td>" + emp.getUsername() + "</td>"
                            + "<td>" + emp.getAddress() + "</td>"
                            + "<td>" + emp.getContact() + "</td>"
                            + "</tr>");
                }
            %>
        </table>

        <hr>

        <div align="center">
            <h1 class="user-list-h1">Delete user</h1>
            <form method="post" action="<%request.getContextPath();%>/user-list">
                <span>Input username:</span>
                <input type="text" name="username_delete" pattern=".{1,}" required>
                <button name="action" value="delete">Delete</button>
            </form>
        </div>

        <br><br><hr>

        <div align="center">
            <h1 class="user-list-h1">Update user</h1>
            <form method="post" action="<%request.getContextPath();%>/user-list">
                <span>Insert user data:</span>
                <button name="action" value="update">Update</button>
                <table>
                    <!-- Username -->
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="username" pattern=".{1,}" required></td>
                    </tr>
                    <!-- Password -->
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <!-- First name -->
                    <tr>
                        <td>First name</td>
                        <td><input type="text" name="firstname"></td>
                    </tr>
                    <!-- Last name -->
                    <tr>
                        <td>Last name</td>
                        <td><input type="text" name="lastname"></td>
                    </tr>
                    <!-- Address -->
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="address"></td>
                    </tr>
                    <!-- Contact -->
                    <tr>
                        <td>Contact</td>
                        <td><input type="text" name="contact"></td>
                    </tr>
                </table>
                <p>This function finds the employee by his username!</p>
            </form>
        </div>
    </div>
</body>
</html>
