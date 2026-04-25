package controller;

import dao.DataAccessLayer;
import model.Employee;

public class EmployeeManagementController {

    private DataAccessLayer dal;
    private AuthenticationController auth;

    public EmployeeManagementController(DataAccessLayer dal, AuthenticationController auth) {
        this.dal = dal;
        this.auth = auth;
    }

    private boolean isHRAdmin() {
        return true;
    }

    public void addEmployee(Employee emp) {
        if (!isHRAdmin()) {
            System.out.println("Access denied");
            return;
        }
        dal.insertEmployee(emp);
    }

    public void updateEmployee(int empID, Employee newData) {
        if (!isHRAdmin()) {
            System.out.println("Access denied");
            return;
        }
        dal.updateEmployee(empID, newData);
    }

    public void deleteEmployee(int empID) {
        if (!isHRAdmin()) {
            System.out.println("Access denied");
            return;
        }
        dal.deleteEmployee(empID);
    }
}