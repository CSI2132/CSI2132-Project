package csi2132.dentist.DentalOffice.model;

import java.sql.Date;
import java.util.Currency;

public class Treatment {
    private Integer treatment_id;
    private String appointment_type;
    private String treatment_type;
    private String medication;
    private String symptoms;
    private String tooth;
    private String comments;
    private Integer appointment_id;
    private Date treatment_date;
    private String treatment_description;
    private String tooth_involved;
    private Currency procedure_amount;
    private Currency patient_charge;
    private Currency insurance_charge;
    private Currency total_charge;

    // Getters and Setters
    public Integer getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(Integer treatment_id) {
        this.treatment_id = treatment_id;
    }

    public String getAppointment_type() {
        return appointment_type;
    }

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public String getTreatment_type() {
        return treatment_type;
    }

    public void setTreatment_type(String treatment_type) {
        this.treatment_type = treatment_type;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTooth() {
        return tooth;
    }

    public void setTooth(String tooth) {
        this.tooth = tooth;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Integer appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Date getTreatment_date() {
        return treatment_date;
    }

    public void setTreatment_date(Date treatment_date) {
        this.treatment_date = treatment_date;
    }

    public String getTreatment_description() {
        return treatment_description;
    }

    public void setTreatment_description(String treatment_description) {
        this.treatment_description = treatment_description;
    }

    public String getTooth_involved() {
        return tooth_involved;
    }

    public void setTooth_involved(String tooth_involved) {
        this.tooth_involved = tooth_involved;
    }

    public Currency getProcedure_amount() {
        return procedure_amount;
    }

    public void setProcedure_amount(Currency procedure_amount) {
        this.procedure_amount = procedure_amount;
    }

    public Currency getPatient_charge() {
        return patient_charge;
    }

    public void setPatient_charge(Currency patient_charge) {
        this.patient_charge = patient_charge;
    }

    public Currency getInsurance_charge() {
        return insurance_charge;
    }

    public void setInsurance_charge(Currency insurance_charge) {
        this.insurance_charge = insurance_charge;
    }

    public Currency getTotal_charge() {
        return total_charge;
    }

    public void setTotal_charge(Currency total_charge) {
        this.total_charge = total_charge;
    }
}
