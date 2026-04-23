package model;

import java.util.Date;
/**
 * Employe.java - 
 * Data container representing any employee record from the database. 
 * Created when DataAccessLayer reads from database. 
 * Stores employee information and provides getters/setters for access. 
 * Used by controllers and DataAccessLayer to pass employee data between layers.
 * 
 */
public class Employee {
    private int empID, addressID;
    private String fname, lname, email, SSN, username, password;
    private Date hireDate;
    private double salary;

    public Employee(int empID, int addressID, String fname, String lname, 
        String email, Date hiredate, String SSN, double salary, String username, String password) 
    {
        this.empID = empID;
        this.addressID = addressID;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.hireDate = hiredate;
        this.SSN = SSN;
        this.salary = salary;
        this.username = username;
        this.password = password;
    }

    // Getters
    public int getEmpID() {
        return empID;
    }
    public int getAddressID() {
        return addressID;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getEmail() {
        return email;
    }
    public Date getHireDate() {
        return hireDate;
    }
    public String getSSN() {
        return SSN;
    }
    public double getSalary() {
        return salary;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    // setters
    public void setSSN(String SSN) { this.SSN = SSN; }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }
    
    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return empID + "," + fname + "," + lname + "," + email + "," +
               hireDate + "," + salary + "," + SSN + "," + addressID + 
               "," + username + "," + password;
    }
    
}
