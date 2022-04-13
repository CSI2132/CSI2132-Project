package csi2132.dentist.DentalOffice.model;

public class Record {
    private String progress_notes;
    private Integer patient_user_id;
    private Integer treatment_id;

    // Getters and Setters
    public String getProgress_notes() {
        return progress_notes;
    }

    public void setProgress_notes(String progress_notes) {
        this.progress_notes = progress_notes;
    }

    public Integer getPatient_user_id() {
        return patient_user_id;
    }

    public void setPatient_user_id(Integer patient_user_id) {
        this.patient_user_id = patient_user_id;
    }

    public Integer getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(Integer treatment_id) {
        this.treatment_id = treatment_id;
    }
}
