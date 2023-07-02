package app.entities;

import app.model.Employee;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeDAO {
    private static EmployeeDAO instance;
    private static final Connection connection;
    static{
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static EmployeeDAO getInstance(){
        if (instance == null){
            instance = new EmployeeDAO();
        }
        return instance;
    }
    private EmployeeDAO(){}
    private int getIdCount(){
        int id = 0;
        try {
            Statement statement = connection.createStatement();
            id = statement.executeUpdate("Select max(id) from employee");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    private Employee getEmployeeByUsername(String username){
        Employee employee = null;
        String sql = "Select * from employee where username=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                employee = getParametersAndCreateNewEmployee(rs);
                if (employee.getUsername()==null) return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    private void setParametersForEmployee(Map<String,String> list, Employee employee){
        if (employee != null){
            for (String k: list.keySet()){
                if (k.equals("firstname") && !list.get(k).equals("")) employee.setFirstName(list.get(k));
                if (k.equals("lastname") && !list.get(k).equals("")) employee.setLastName(list.get(k));
                if (k.equals("password") && !list.get(k).equals("")) employee.setPassword(list.get(k));
                if (k.equals("address") && !list.get(k).equals("")) employee.setAddress(list.get(k));
                if (k.equals("contact") && !list.get(k).equals("")) employee.setContact(list.get(k));
            }
        }
    }
    private Employee getParametersAndCreateNewEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("username"),
                rs.getString("pass"),
                rs.getString("address"),
                rs.getString("contact")
        );
    }
    public Employee login(String username,String password){
        String sql = "SELECT * FROM employee where username=? and pass=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username); //первый знак вопроса
            preparedStatement.setString(2, password);   //второй знак вопроса
            ResultSet rs = preparedStatement.executeQuery();


            if (!username.equals("") && !password.equals("")){
                if (rs.next())
                    return getParametersAndCreateNewEmployee(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Employee> getUsersList(){
        List<Employee> list = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM employee");
            while(rs.next()) {
                list.add(getParametersAndCreateNewEmployee(rs));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public int checkUsername(Employee employee){
        String sql  = "SELECT * FROM employee where username=? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getUsername()); //первый знак вопроса
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                if (rs.getString("username").equals(employee.getUsername())){
                    return 1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public void deleteEmployee(String username){
        if (username.equals("")) return;
        String sql = "delete from employee where username=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username); //первый знак вопроса
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateEmployee(Map<String,String> list,String username){
        Employee employee = getEmployeeByUsername(username);
        setParametersForEmployee(list,employee);

        String updateSql = "update employee set first_name=?, last_name=?, username=?, pass=?, address=?, contact=? " +
                "where id = (select id from employee where username='"+ username + "');";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            if (employee != null) {
                preparedStatement.setString(1,employee.getFirstName());
                preparedStatement.setString(2,employee.getLastName());
                preparedStatement.setString(3,employee.getUsername());
                preparedStatement.setString(4,employee.getPassword());
                preparedStatement.setString(5,employee.getAddress());
                preparedStatement.setString(6,employee.getContact());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void  registerEmployee(Employee employee) {
        String INSERT_USERS_SQL = "INSERT INTO employee"+
                " (id,first_name,last_name,username,pass,address,contact) values " +
                "(?,?,?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setInt(1,instance.getIdCount());
            preparedStatement.setString(2,employee.getFirstName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setString(4,employee.getUsername());
            preparedStatement.setString(5,employee.getPassword());
            preparedStatement.setString(6,employee.getAddress());
            preparedStatement.setString(7,employee.getContact());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}