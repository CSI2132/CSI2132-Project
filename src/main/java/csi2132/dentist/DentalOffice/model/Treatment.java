package csi2132.dentist.DentalOffice.model;

import java.sql.Date;

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
    private Double procedure_amount;
    private Double patient_charge; 
    private Double insurance_charge; 
    private Double total_charge; 

    public Treatment(Integer treatment_id, String appointment_type, String treatment_type, 
            String medication, String symptoms, String tooth, String comments, Integer appointment_id, 
            Date treatment_date, String treatment_description, String tooth_involved, Double procedure_amount, 
            Double patient_charge, Double insurance_charge, Double total_charge) {
        this.treatment_id = treatment_id;
        this.appointment_type = appointment_type;
        this.treatment_type = treatment_type;
        this.medication = medication;
        this.symptoms = symptoms;
        this.tooth = tooth;
        this.comments = comments;
        this.appointment_id = appointment_id;
        this.treatment_date = treatment_date;
        this.treatment_description = treatment_description;
        this.tooth_involved = tooth_involved;
        this.procedure_amount = procedure_amount;
        this.patient_charge = patient_charge;
        this.insurance_charge = insurance_charge;
        this.total_charge = total_charge;
    }

    public Integer getTreatmentId() {
        return treatment_id;
    }

    public String getAppointmentType() {
        return appointment_type;
    }

    public String getTreatmentType() {
        return treatment_type;
    }

    public String getMedication() {
        return medication;
    }

<<<<<<< HEAD
=======
    public void setMedication(String medication) {
        this.medication = medication;
    }

>>>>>>> 3476ec1b00aefb5a49b169f4fadc98c1200f3a39
    public String getSymptoms() {
        return symptoms;
    }

<<<<<<< HEAD
=======
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

>>>>>>> 3476ec1b00aefb5a49b169f4fadc98c1200f3a39
    public String getTooth() {
        return tooth;
    }

<<<<<<< HEAD
=======
    public void setTooth(String tooth) {
        this.tooth = tooth;
    }

>>>>>>> 3476ec1b00aefb5a49b169f4fadc98c1200f3a39
    public String getComments() {
        return comments;
    }

<<<<<<< HEAD
    public Integer getAppointmentId() {
        return appointment_id;
    }

    public Date getTreatmentDate() {
        return treatment_date;
    }

    public String getTreatmentDescription() {
        return treatment_description;
    }

    public String getToothInvolved() {
        return tooth_involved;
    }

    public Double getProcedureAmount() {
        return procedure_amount;
    }

    public Double getPatientCharge() {
        return patient_charge;
    }

    public Double getInsuranceCharge() {
        return insurance_charge;
    }

    public Double getTotalCharge() {
        return total_charge;
    }
=======
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
>>>>>>> 3476ec1b00aefb5a49b169f4fadc98c1200f3a39
}
