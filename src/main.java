
/**
 * main.java - Entry point of application. Creates instances of all controllers and the UI,
 * then starts the program.
 */

import controller.AuthenticationController;
import controller.EmployeeManagementController;
import controller.ReportGenerator;
import controller.SearchController;
import dao.DataAccessLayer;
import view.UserInterface;

public class Main {

    public static void main(String[] args) {

        DataAccessLayer dal = new DataAccessLayer();
        dal.connect();

        AuthenticationController auth = new AuthenticationController(dal);
        SearchController search = new SearchController(auth, dal);
        EmployeeManagementController empMgmt = new EmployeeManagementController(auth, dal);
        ReportGenerator report = new ReportGenerator(dal, auth);

        UserInterface ui = new UserInterface(auth, search, empMgmt, report, dal);
        ui.start();

        dal.disconnect();
    }


    
}
