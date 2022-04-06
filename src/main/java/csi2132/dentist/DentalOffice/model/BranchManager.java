package csi2132.dentist.DentalOffice.model;

public class BranchManager extends User {
    private String first_name;
    private String last_name;
    private String branch_manager_address;
    private String branch_manager_role;
    private String SSN;
    private Integer salary;
    private Integer branch_id;

    // Getters and setters

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBranch_manager_address() {
        return branch_manager_address;
    }

    public void setBranch_manager_address(String branch_manager_address) {
        this.branch_manager_address = branch_manager_address;
    }

    public String getBranch_manager_role() {
        return branch_manager_role;
    }

    public void setBranch_manager_role(String branch_manager_role) {
        this.branch_manager_role = branch_manager_role;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }
}
