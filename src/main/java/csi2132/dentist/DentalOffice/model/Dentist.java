package csi2132.dentist.DentalOffice.model;

public class Dentist extends Employee { //Change this to extends Employee, which will also then get access to user?
    private String first_name;
    private String last_name;
    private String dentist_address;
    private String dentist_role;
    private String SSN;
    private Integer salary;
    private Integer branch_id;
    private String speciality;

    // getters and setters
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

    public String getDentist_address() {
        return dentist_address;
    }

    public void setDentist_address(String dentist_address) {
        this.dentist_address = dentist_address;
    }

    public String getDentist_role() {
        return dentist_role;
    }

    public void setDentist_role(String dentist_role) {
        this.dentist_role = dentist_role;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
