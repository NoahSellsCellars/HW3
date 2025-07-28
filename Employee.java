public class Employee {
    private String name;
    private String department;
    private String position;
    private int salary;
    private int tenure;
    private boolean isDepartmentLead;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;   
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getTenure() {
        return tenure;
    }
    public void setTenure(int tenure) {
        this.tenure = tenure;
    }
    public boolean isDepartmentLead() {
        return isDepartmentLead;
    }
    public void setDepartmentLead(boolean isDepartmentLead) {
        this.isDepartmentLead = isDepartmentLead;
    }
    public String getInfo() {
        return "Name: " + name + ", Department: " + department +
               ", Salary: $" + salary + ", Tenure: " + tenure + " years" +
               ", Department Lead: " + (isDepartmentLead ? "Yes" : "No");
    }
    public String getNameAndSalary() {
        return "Name: " + name + ", Salary: $" + salary;
    }
    
}
