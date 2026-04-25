import controller.EmployeeManagementController;
import controller.AuthenticationController;
import dao.DataAccessLayer;
import model.Employee;

public class TestEmployeeManagement {

    public static void main(String[] args) {

        DataAccessLayer dal = new DataAccessLayer();
        AuthenticationController auth = new AuthenticationController(dal);

        EmployeeManagementController emc = new EmployeeManagementController(auth, dal);

        // TEMP TEST (avoid constructor errors)
        Employee emp = null;

        boolean result = emc.addEmployee(emp);

        System.out.println("Add employee result: " + result);
    }
}
