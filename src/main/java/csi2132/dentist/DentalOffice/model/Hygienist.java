package csi2132.dentist.DentalOffice.model;

public class Hygienist extends User { 
    private String first_name;
    private String last_name;
    private String hygienist_address;
    private String hygienist_role;
    private String SSN;
    private Integer salary;
    private Integer branch_id;
    private String seniority;

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

    public String getHygienist_address() {
        return hygienist_address;
    }

    public void setHygienist_address(String hygienist_address) {
        this.hygienist_address = hygienist_address;
    }

    public String getHygienist_role() {
        return hygienist_role;
    }

    public void setHygienist_role(String hygienist_role) {
        this.hygienist_role = hygienist_role;
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

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }
}
