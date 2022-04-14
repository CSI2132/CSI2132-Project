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

    public String getSymptoms() {
        return symptoms;
    }

    public String getTooth() {
        return tooth;
    }

    public String getComments() {
        return comments;
    }

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
}
