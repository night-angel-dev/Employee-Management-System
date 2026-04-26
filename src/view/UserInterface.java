package view;

import controller.*;
import dao.DataAccessLayer;
import java.util.List;
import java.util.Scanner;
import model.Employee;

public class UserInterface {

    private final AuthenticationController auth;
    private final SearchController search;
    private final EmployeeManagementController empMgmt;
    private final ReportGenerator reports;
    private final DataAccessLayer dal;

    private final Scanner sc;

    public UserInterface(AuthenticationController auth,
                         SearchController search,
                         EmployeeManagementController empMgmt,
                         ReportGenerator reports,
                         DataAccessLayer dal) {
        this.auth = auth;
        this.search = search;
        this.empMgmt = empMgmt;
        this.reports = reports;
        this.dal = dal;
        this.sc = new Scanner(System.in);
    }

    // start application
    public void start() {
        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Login");
            System.out.println("2. Exit");

            int choice = getIntInput();

            if (choice == 1) {
                loginFlow();
            } else if (choice == 2) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // login flow - re-prompts on failure until user cancels or succeeds
    private void loginFlow() {
        while (true) {
            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            if (auth.login(username, password)) {
                if (auth.isHRAdmin()) {
                    hrMenu();
                }
                else {
                    employeeMenu();
                } 
                return;
            }

            System.out.println("Login failed. Try again? (y/n): ");
            
            String response = sc.nextLine().trim();
            
            if (!response.equalsIgnoreCase("y")) {
                return;  // Exit if user didn't type 'y' or 'Y'
            }
        }
    }

    // general employee menu
    private void employeeMenu() {
        while (auth.isLoggedIn()) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Search Employee by ID");
            System.out.println("2. Search Employee by Name");
            System.out.println("3. View My Pay History");
            System.out.println("4. Logout");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = getIntInput();
                    Employee emp = search.searchByEmpID(id);
                    System.out.println(emp);
                    break;

                case 2:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    List<Employee> list = search.searchByName(name);
                    list.forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("\n--- Pay History ---");
                    reports.getMyPayHistory().forEach(System.out::println);
                    break;

                case 4:
                    auth.logout();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // HR admin menu
    private void hrMenu() {
        while (auth.isLoggedIn()) {
            System.out.println("\n--- HR Admin Menu ---");
            System.out.println("1. Search Employee");
            System.out.println("2. Add Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. View Any Pay History");
            System.out.println("6. Salary Update by Percentage (Multiple Employees)");
            System.out.println("7. Logout");

            int choice = getIntInput();

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    List<Employee> results = search.searchByName(name);
                    results.forEach(System.out::println);

                    if (!results.isEmpty()) {
                        System.out.print("Enter employee ID to update (0 to skip): ");
                        int selectedID = getIntInput();
                        
                        if (selectedID != 0) { 
                            updateEmployeeFlow(selectedID);
                        }
                    }
                    break;

                case 2:
                    Employee newEmp = createEmployeeInput();
                    empMgmt.addEmployee(newEmp);
                    break;

                case 3:
                    updateEmployeeFlow();
                    break;

                case 4:
                    deleteEmployeeFlow();
                    break;

                case 5:
                    viewPayHistoryMenu();
                    break;

                case 6:
                    System.out.print("Percentage increase (e.g. 5 for 5%): ");
                    double percent = getDoubleInput();
                    System.out.print("Min salary: ");
                    double minSalary = getDoubleInput();
                    System.out.print("Max salary: ");
                    double maxSalary = getDoubleInput();
                    empMgmt.updateSalaryByPercentage(percent, minSalary, maxSalary);
                    break;

                case 7:
                    auth.logout();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // HELPER: Create Employee
    private Employee createEmployeeInput() {

        System.out.print("First Name: ");
        String fname = sc.nextLine();

        System.out.print("Last Name: ");
        String lname = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Salary: ");
        double salary = getDoubleInput();

        System.out.print("SSN: ");
        String ssn = sc.nextLine();

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        // Address fields a new address row is inserted and its generated ID is used
        System.out.println("\n-- Address Details --");
        System.out.print("Street: ");
        String street = sc.nextLine();

        System.out.print("City: ");
        String cityName = sc.nextLine();

        System.out.print("State (2-letter abbreviation, e.g. CA): ");
        String stateAbbr = sc.nextLine();

        System.out.print("Zip Code: ");
        String zip = sc.nextLine();

        System.out.print("Date of Birth (YYYY-MM-DD): ");
        java.sql.Date dob = java.sql.Date.valueOf(sc.nextLine());

        System.out.print("Mobile Number: ");
        String mobile = sc.nextLine();

        System.out.print("Emergency Contact Name: ");
        String emergName = sc.nextLine();

        System.out.print("Emergency Contact Phone: ");
        String emergPhone = sc.nextLine();

        int cityID    = dal.findOrCreateCity(cityName);
        int stateID   = dal.findOrCreateState(stateAbbr);
        int addressID = dal.insertAddress(street, cityID, stateID, zip, dob, mobile, emergName, emergPhone);

        return new Employee(
            0,
            addressID,
            fname,
            lname,
            email,
            new java.util.Date(),
            ssn,
            salary,
            username,
            password
        );
    }

    // Update a single field on an existing employee
    private void updateEmployeeFlow() {
        System.out.print("Enter Employee ID to update: ");
        updateEmployeeFlow(getIntInput());
    }

    private void updateEmployeeFlow(int empID) {
        Employee current = search.searchByEmpID(empID);
        if (current == null) return;

        System.out.println("\nCurrent record: " + current);

        boolean done = false;
        while (!done) {
            System.out.println("\nWhich field would you like to update?");
            System.out.println("1. First Name  (current: " + current.getFname() + ")");
            System.out.println("2. Last Name   (current: " + current.getLname() + ")");
            System.out.println("3. Email       (current: " + current.getEmail() + ")");
            System.out.println("4. Salary      (current: " + current.getSalary() + ")");
            System.out.println("5. SSN         (current: " + current.getSSN() + ")");
            System.out.println("6. Username    (current: " + current.getUsername() + ")");
            System.out.println("7. Password");
            System.out.println("8. Save and exit");

            switch (getIntInput()) {
                case 1: System.out.print("New First Name: ");  current.setFname(sc.nextLine());    break;
                case 2: System.out.print("New Last Name: ");   current.setLname(sc.nextLine());    break;
                case 3: System.out.print("New Email: ");       current.setEmail(sc.nextLine());    break;
                case 4: System.out.print("New Salary: ");      current.setSalary(getDoubleInput()); break;
                case 5: System.out.print("New SSN: ");         current.setSSN(sc.nextLine());      break;
                case 6: System.out.print("New Username: ");    current.setUsername(sc.nextLine()); break;
                case 7: System.out.print("New Password: ");    current.setPassword(sc.nextLine()); break;
                case 8: done = true; break;
                default: System.out.println("Invalid option.");
            }
        }
        empMgmt.updateEmployee(empID, current);
    }

    // Show employee record and ask for confirmation before deleting
    private void deleteEmployeeFlow() {
        System.out.print("Enter Employee ID to delete: ");
        int empID = getIntInput();
        Employee toDelete = search.searchByEmpID(empID);
       
        if (toDelete == null) return;

        System.out.println("\nEmployee record:");
        System.out.println(toDelete);
        System.out.print("Are you sure you want to delete this employee? (y/n): ");
        
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            empMgmt.deleteEmployee(empID);
        } 
        else {
            System.out.println("Delete cancelled.");
        }
    }

    // Pay history sub-menu covering all four report methods
    private void viewPayHistoryMenu() {
        while (true) {
            System.out.println("\n--- Pay History Reports ---");
            System.out.println("1. Pay History for Employee");
            System.out.println("2. Total Pay by Job Title");
            System.out.println("3. Total Pay by Division");
            System.out.println("4. New Hires by Date Range");
            System.out.println("5. Back");

            switch (getIntInput()) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    reports.getPayHistory(getIntInput()).forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Job Title ID: ");
                    int jobTitleId = getIntInput();
                    System.out.print("Month (1-12): ");
                    int jtMonth = getIntInput();
                    System.out.print("Year (e.g. 2024): ");
                    int jtYear = getIntInput();
                    System.out.printf("Total Pay: $%.2f%n", reports.getTotalPayByJobTitle(jobTitleId, jtMonth, jtYear));
                    break;
                case 3:
                    System.out.print("Division ID: ");
                    int divID = getIntInput();
                    System.out.print("Month (1-12): ");
                    int divMonth = getIntInput();
                    System.out.print("Year (e.g. 2024): ");
                    int divYear = getIntInput();
                    System.out.printf("Total Pay: $%.2f%n", reports.getTotalPayByDivision(divID, divMonth, divYear));
                    break;
                case 4:
                    System.out.print("Start Date (YYYY-MM-DD): ");
                    java.sql.Date start = java.sql.Date.valueOf(sc.nextLine());
                    System.out.print("End Date (YYYY-MM-DD): ");
                    java.sql.Date end = java.sql.Date.valueOf(sc.nextLine());
                    List<Employee> hires = reports.getNewHiresByDateRange(start, end);
                    System.out.println("New hires found: " + hires.size());
                    hires.forEach(System.out::println);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Input Helpers
    private int getIntInput() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine());
                return value;
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}
