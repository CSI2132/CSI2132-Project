package csi2132.dentist.DentalOffice.model;

import java.sql.Date;
import java.util.Currency;

public class AppointmentProcedure {
    public Integer appointment_id;
    public Integer procedure_id;
    public Date appointment_procedure_date;
    public String appointment_procedure_description;
    public String tooth_involved;
    public Currency procedure_amount;
    public Currency patient_charge;
    public Currency insurance_charge; 
    public Currency total_charge; 
}