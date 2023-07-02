package app.servlets;

import app.entities.EmployeeDAO;
import app.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {
    private final EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/employee_register.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = getParametersAndCreateEmployee(req);

        int a = employeeDAO.checkUsername(employee);
        if (a == 0) {
            employeeDAO.registerEmployee(employee);
            setSessionAttribute(req,employee);
            getServletContext().getRequestDispatcher("/views/home.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("message","This user already exists. Please <a href=\\\"http://localhost:8080/login?\\\">login</a>");
            getServletContext().getRequestDispatcher("/views/employee_details.jsp").forward(req,resp);
        }
    }
    private void setSessionAttribute(HttpServletRequest req,Employee employee){
        //Set session attributes
        HttpSession session = req.getSession();
        session.setAttribute("user",employee);
    }
    private Employee getParametersAndCreateEmployee(HttpServletRequest req){
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String contact = req.getParameter("contact");

        return new Employee(firstName,lastName,username,password,address,contact);
    }
}
