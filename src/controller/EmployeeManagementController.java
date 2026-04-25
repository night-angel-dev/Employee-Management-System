package controller;

import dao.DataAccessLayer;
import model.Employee;

public class EmployeeManagementController {

    private AuthenticationController auth;
    private DataAccessLayer dal;

    public EmployeeManagementController(AuthenticationController auth, DataAccessLayer dal) {
        this.auth = auth;
        this.dal = dal;
    }

    private boolean isHRAdmin() {
        return auth != null && auth.isHRAdmin();
    }

    // ➕ Add Employee
    public boolean addEmployee(Employee emp) {
        if (!isHRAdmin()) {
            System.out.println("Access denied.");
            return false;
        }

        dal.insertEmployee(emp); 
        return true;             
    }

    // ✏️ Update Employee
    public boolean updateEmployee(int empID, Employee newData) {
        if (!isHRAdmin()) {
            System.out.println("Access denied.");
            return false;
        }

        dal.updateEmployee(empID, newData);
        return true;
    }

    // ❌ Delete Employee
    public boolean deleteEmployee(int empID) {
        if (!isHRAdmin()) {
            System.out.println("Access denied.");
            return false;
        }

        dal.deleteEmployee(empID);
        return true;
    }
}