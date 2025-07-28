import java.util.ArrayList;

public class Department {
    private String departmentName;
    private ArrayList<Employee> departmentLeads = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();

    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public ArrayList<Employee> getDepartmentLeads() {
        return departmentLeads;
    }
    public void addDepartmentLead(Employee departmentLead) {
        departmentLeads.add(departmentLead);
    }
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    //Lowest salary in the department
    public String getLowestSalary() {
        String lowSalaryEmployees = "";
        if (employees.isEmpty()) {
            return null;
        }
        Employee lowest = employees.get(0);
        for (Employee emp : employees) {
            if (emp.getSalary() < lowest.getSalary()) {
                lowest = emp;
            }
        }
        for (Employee emp : employees) {
            if (emp.getSalary() == lowest.getSalary()) {
                lowSalaryEmployees += emp.getNameAndSalary() + "\n";
            }
        }
        return lowSalaryEmployees;
    }
// Employees with less than a certain number of years of tenure
    public String getEmployeesUnderTenure(int years) {
        String underTenureEmployees = "";
        for (Employee emp : employees) {
            if (emp.getTenure() < years) {
                underTenureEmployees += emp.getInfo() + "\n";
            }
        }
        if (underTenureEmployees.isEmpty()) {
            return "No employees with less than " + years + " years of tenure.";
        }
        return underTenureEmployees;    
    }

    
    public double averageSalary() {
        if (employees.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (Employee emp : employees) {
            total += emp.getSalary();
        }
        return total / employees.size();
    }

    
}
