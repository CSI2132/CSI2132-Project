package csi2132.dentist.DentalOffice.model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {

    private Integer appointment_id;
    private Integer patient_user_id;
    private Integer dentist_user_id;
    private Integer hygienist_user_id;
    private Date appointment_date;
    private Time start_time;
    private Time end_time;
    private String appointment_type; 
    private String appointment_status;
    private String assigned_room;

    // Getters and Setters

    public Integer getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Integer appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Integer getPatient_user_id() {
        return patient_user_id;
    }

    public void setPatient_user_id(Integer patient_user_id) {
        this.patient_user_id = patient_user_id;
    }

    public Integer getDentist_user_id() {
        return dentist_user_id;
    }

    public void setDentist_user_id(Integer dentist_user_id) {
        this.dentist_user_id = dentist_user_id;
    }

    public Integer getHygienist_user_id() {
        return hygienist_user_id;
    }

    public void setHygienist_user_id(Integer hygienist_user_id) {
        this.hygienist_user_id = hygienist_user_id;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public String getAppointment_type() {
        return appointment_type;
    }

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public String getAppointment_status() {
        return appointment_status;
    }

    public void setAppointment_status(String appointment_status) {
        this.appointment_status = appointment_status;
    }

    public String getAssigned_room() {
        return assigned_room;
    }


    public void setAssigned_room(String assigned_room) {
        this.assigned_room = assigned_room;
    }
}