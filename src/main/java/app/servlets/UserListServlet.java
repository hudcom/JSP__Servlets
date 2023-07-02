package app.servlets;

import app.entities.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/user-list")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null){
            getServletContext().getRequestDispatcher("/views/employee_login.jsp").forward(req,resp);
        } else {
            getServletContext().getRequestDispatcher("/views/user_list.jsp").forward(req,resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("update")){
            Map<String,String> list = getParameters(req);
            EmployeeDAO.getInstance().updateEmployee(list,req.getParameter("username"));
            getServletContext().getRequestDispatcher("/views/user_list.jsp").forward(req,resp);
        }
        else if (req.getParameter("action").equals("delete")){
            EmployeeDAO.getInstance().deleteEmployee(req.getParameter("username_delete"));
            getServletContext().getRequestDispatcher("/views/user_list.jsp").forward(req,resp);
        }
    }
    private Map<String,String> getParameters(HttpServletRequest req){
        Map<String,String> list = new HashMap<>();
        if (req.getParameter("firstname")!=null) list.put("firstname",req.getParameter("firstname"));
        if (req.getParameter("lastname")!=null) list.put("lastname",req.getParameter("lastname"));
        if (req.getParameter("password")!=null) list.put("password",req.getParameter("password"));
        if (req.getParameter("address")!=null) list.put("address",req.getParameter("address"));
        if (req.getParameter("contact")!=null) list.put("contact",req.getParameter("contact"));
        return list;
    }
}
