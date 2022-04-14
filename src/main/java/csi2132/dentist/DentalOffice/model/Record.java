package csi2132.dentist.DentalOffice.model;

public class Record {
    private String progress_notes;
    private Integer patient_user_id;
    private Integer treatment_id;

    // Constructor
    public Record(String progress_notes, Integer patient_user_id, Integer treatment_id) {
        this.progress_notes = progress_notes;
        this.patient_user_id = patient_user_id;
        this.treatment_id = treatment_id;
    }

    public String getProgressNotes() {
        return progress_notes;
    }

    public Integer getPatientUserId() {
        return patient_user_id;
    }

    public Integer getTreatmentId() {
        return treatment_id;
    }

    public void setPatientUserId(int user_id) {
        patient_user_id = user_id;
    }

    public void setTreatmentId(int treatment_id) {
        this.treatment_id = treatment_id;
    }

}
