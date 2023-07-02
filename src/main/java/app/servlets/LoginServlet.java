package app.servlets;

import app.entities.EmployeeDAO;
import app.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null)
            getServletContext().getRequestDispatcher("/views/employee_login.jsp").forward(req,resp);
        else
            getServletContext().getRequestDispatcher("/views/home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Employee employee = EmployeeDAO.getInstance().login(username,password);
        resp.setContentType("text/html");
        if (employee != null){
            req.getSession().setAttribute("user",employee);
            getServletContext().getRequestDispatcher("/views/home.jsp").forward(req,resp);
        } else{
            req.setAttribute("message","This user does not exist. Please <a href=\\\"http://localhost:8080/register?\\\">register</a>");
            getServletContext().getRequestDispatcher("/views/employee_details.jsp").forward(req,resp);
        }



    }
}
