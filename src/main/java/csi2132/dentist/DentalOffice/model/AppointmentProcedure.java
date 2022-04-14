package csi2132.dentist.DentalOffice.model;

import java.sql.Date;
import java.util.Currency;

public class AppointmentProcedure {
    private Integer appointment_id;
    private Integer procedure_id;
    private Date appointment_procedure_date;
    private String appointment_procedure_description;
    private String tooth_involved;
    private Double procedure_amount;
    private Double patient_charge;
    private Double insurance_charge;
    private Double total_charge;

    // Getters and Setters

    public Integer getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Integer appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Integer getProcedure_id() {
        return procedure_id;
    }

    public void setProcedure_id(Integer procedure_id) {
        this.procedure_id = procedure_id;
    }

    public Date getAppointment_procedure_date() {
        return appointment_procedure_date;
    }

    public void setAppointment_procedure_date(Date appointment_procedure_date) {
        this.appointment_procedure_date = appointment_procedure_date;
    }

    public String getAppointment_procedure_description() {
        return appointment_procedure_description;
    }

    public void setAppointment_procedure_description(String appointment_procedure_description) {
        this.appointment_procedure_description = appointment_procedure_description;
    }

    public String getTooth_involved() {
        return tooth_involved;
    }

    public void setTooth_involved(String tooth_involved) {
        this.tooth_involved = tooth_involved;
    }

    public Double getProcedure_amount() {
        return procedure_amount;
    }

    public void setProcedure_amount(Double procedure_amount) {
        this.procedure_amount = procedure_amount;
    }

    public Double getPatient_charge() {
        return patient_charge;
    }

    public void setPatient_charge(Double patient_charge) {
        this.patient_charge = patient_charge;
    }

    public Double getInsurance_charge() {
        return insurance_charge;
    }

    public void setInsurance_charge(Double insurance_charge) {
        this.insurance_charge = insurance_charge;
    }

    public Double getTotal_charge() {
        return total_charge;
    }

    public void setTotal_charge(Double total_charge) {
        this.total_charge = total_charge;
    }
}