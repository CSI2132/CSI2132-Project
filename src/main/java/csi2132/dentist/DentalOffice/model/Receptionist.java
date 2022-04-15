package csi2132.dentist.DentalOffice.model;

public class Receptionist extends User {
    private String first_name;
    private String last_name;
    private String receptionist_address;
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

    public String getReceptionist_address() {
        return receptionist_address;
    }

    public void setReceptionist_address(String receptionist_address) {
        this.receptionist_address = receptionist_address;
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
