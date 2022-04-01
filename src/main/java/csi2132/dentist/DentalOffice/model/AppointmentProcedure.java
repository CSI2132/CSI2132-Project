package csi2132.dentist.DentalOffice.model;

import java.sql.Date;
import java.util.Currency;

public class AppointmentProcedure {
    private Integer appointment_id;
    private Integer procedure_id;
    private Date appointment_procedure_date;
    private String appointment_procedure_description;
    private String tooth_involved;
    private Currency procedure_amount;
    private Currency patient_charge;
    private Currency insurance_charge; 
    private Currency total_charge; 
    
}