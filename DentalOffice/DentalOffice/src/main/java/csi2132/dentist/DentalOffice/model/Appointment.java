package csi2132.dentist.DentalOffice.model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private String appointment_id;
    private String patient_user_id;
    private String dentist_user_id;
    private String hygienist_user_id;
    private Date appointment_date;
    private Time start_time;
    private Time end_time;
    private String appointment_type;
    private String appointment_status;
    private String assigned_room;
}