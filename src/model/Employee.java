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
    private String fname, lname, email, SSN;
    private Date hireDate;
    private double salary;

    public Employee(int empID, int addressID, String fname, String lname, String email, Date hiredate, String SSN, double salary) {
        this.empID = empID;
        this.addressID = addressID;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.hireDate = hiredate;
        this.SSN = SSN;
        this.salary = salary;
    }

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

    public void setSSN(String SSN) { this.SSN = SSN; }

    public String toString() {
        return empID + "," + fname + "," + lname + "," + email + "," +
               hireDate + "," + salary + "," + SSN + "," + addressID;
    }
    
}
