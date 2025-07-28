import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeInfoApp {

    // Method to find the employee with the longest tenure company wide
    public static ArrayList<Employee> longestTenureCompanyWide(ArrayList<Department> departments) {
        ArrayList<Employee> longestTenureEmployee = new ArrayList<>();
            for (Department department : departments) {
                for (Employee employee : department.getEmployees()) {
                    if (longestTenureEmployee.isEmpty()) {
                        longestTenureEmployee.add(employee);
                    }  else if (employee.getTenure() > longestTenureEmployee.get(0).getTenure()) {
                    longestTenureEmployee.clear();
                    longestTenureEmployee.add(employee);
                    }
                    else if (employee.getTenure() == longestTenureEmployee.get(0).getTenure()) {
                        longestTenureEmployee.add(employee);
                    }
                }
            }
            return longestTenureEmployee;
        }

    // Method to display each department with number of leads
    public static void numberOfLeadsPerDepartment(ArrayList<Department> departments) {
        for (Department department : departments) {
            System.out.println("Department: " + department.getDepartmentName() + 
                               ", Number of Leads: " + department.getDepartmentLeads().size());
        }
    }

    // Method to display the list of departments and their average salary
    public static String averageSalary(ArrayList<Department> departments) {
        StringBuilder result = new StringBuilder();
        for (Department department : departments) {
            //add the department name
            result.append("Department: ").append(department.getDepartmentName()).append("\n");
            //if the department has no employees, append a message
            if (department.getEmployees().isEmpty()) {
                result.append(" - No employees in this department.\n");
            //if the department has employees, calculate and append the average salary
            } else {
                double averageSalary = department.averageSalary();
                //format the average salary to two decimal places and append it
                result.append(String.format("%.2f", averageSalary)).append("\n");
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {

        ArrayList<Department> departments = new ArrayList<>();  //List to hold all departments

        String filepath = "employee_data.csv"; // Path to the employee data file
        File employeeData = new File(filepath);
        try {
            Scanner scanner = new Scanner(employeeData);
            String line = scanner.nextLine(); // Read the header line
            while (scanner.hasNextLine()) {
                // Read each line and split by comma
                // Assuming the format is: FirstName, LastName, Department, Position, Salary, Tenure, IsDepartmentLead
                line = scanner.nextLine();
                String[] fields = line.split(",");
                if (fields != null) {
                    // Create a new Employee object and set its properties
                    Employee employee = new Employee();
                    
                    String name = fields[0].trim() + " " + fields[1].trim();
                    String departmentName = fields[2].trim();
                    String position = fields[3].trim();
                    int salary = Integer.parseInt(fields[4].trim());
                    int tenure = Integer.parseInt(fields[5].trim());
                    boolean isDepartmentLead = fields[6].trim().equalsIgnoreCase("yes");

                    employee.setName(name);
                    employee.setDepartment(departmentName);
                    employee.setPosition(position);
                    employee.setSalary(salary);
                    employee.setTenure(tenure);
                    employee.setDepartmentLead(isDepartmentLead);

                    // Check if the department already exists
                    boolean departmentExists = false;
                    Department existingDepartment = null;
                    for (Department dept : departments) {
                        if (dept.getDepartmentName().equalsIgnoreCase(departmentName)) {
                            departmentExists = true;
                            existingDepartment = dept;
                        }
                    }
                    if (departmentExists) {
                        // Add employee to existing department
                        existingDepartment.addEmployee(employee);
                        if (isDepartmentLead) {
                            existingDepartment.addDepartmentLead(employee);   
                        }
                    }
                    else {
                        // Create a new department and add the employee
                        Department newDepartment = new Department();
                        newDepartment.setDepartmentName(departmentName);
                        newDepartment.addEmployee(employee);
                        if (isDepartmentLead) {
                            newDepartment.addDepartmentLead(employee);
                        }
                        departments.add(newDepartment);
                    }

                }    
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + filepath);
            return; // Exit if file not found
        }

        // Test Lowest Salary
        System.out.println("-------------------\n");
        System.out.println("\nLowest Salary in each department:\n");
        for (Department department : departments) {
            System.out.println("Department: " + department.getDepartmentName());
            System.out.println(department.getLowestSalary());

        }
        
        // Employees with less than 4 years of tenure
        System.out.println("-------------------\n");
        System.out.println("Employees with less than 4 years of tenure:\n");
        for (Department department : departments) {
            System.out.println("Department: " + department.getDepartmentName());
            System.out.println(department.getEmployeesUnderTenure(4));
        }

        // Average Salary of Marketing Department
        // Find the index of the Marketing department
        System.out.println("-------------------\n");
        int index = 0;
        boolean marketingFound= false;
        for (Department department : departments) {
            if (department.getDepartmentName().equalsIgnoreCase("Marketing")) {
                marketingFound = true;
                break;
            }
            else {
                index++;
            }
        }
        // If Marketing department is found, calculate and print the average salary
        if (marketingFound) {
            // Print the average salary of the Marketing department to two decimal places
            System.out.println("Average Salary in Marketing Department: " + "\n" +
                               String.format("%.2f", departments.get(index).averageSalary()));
        } else {
            System.out.println("Marketing Department not found.");  
        }

        // Longest Tenure Company wide
        System.out.println("-------------------\n");
        System.out.println("Longest Tenure Company wide:");
        ArrayList<Employee> longestTenureEmployee = longestTenureCompanyWide(departments);
        for (Employee emp : longestTenureEmployee) {
        System.out.println(emp.getInfo());
        }

        // Number of Leads per Department
        System.out.println("-------------------\n");
        System.out.println("\nNumber of Leads per Department:");
        numberOfLeadsPerDepartment(departments);

        // Average Salary of each department
        System.out.println("-------------------\n");
        System.out.println("\nAverage Salary of each department:");
        String averageSalaries = averageSalary(departments);
        System.out.println(averageSalaries);
    }
}
