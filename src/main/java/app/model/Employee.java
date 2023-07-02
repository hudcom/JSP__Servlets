package app.model;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String address;
    private String contact;
    public Employee(){}
    public Employee(String firstName, String lastName,String userName,
    String password,String address, String contact){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.password = password;
        this.address = address;
        this.contact  = contact;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName)
                && username.equals(employee.username)
                && password.equals(employee.password)
                && Objects.equals(address, employee.address)
                && Objects.equals(contact, employee.contact);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password, address, contact);
    }
}
