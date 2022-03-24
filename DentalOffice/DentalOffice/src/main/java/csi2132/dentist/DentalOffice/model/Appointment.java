package csi2132.dentist.DentalOffice.model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    public String appointment_id;
    public String patient_user_id;
    public String dentist_user_id;
    public String hygienist_user_id;
    public Date appointment_date;
    public Time start_time;
    public Time end_time;
    public String appointment_type;
    public String appointment_status;
    public String assigned_room;
}