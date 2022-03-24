package csi2132.dentist.DentalOffice.model;

import java.sql.Date;
import java.util.Currency;

public class Treatment {
    public Integer treatment_id;
    public String appointment_type;
    public String treatment_type;
    public String medication;
    public String symptoms;
    public String tooth;
    public String comments;
    public Integer appointment_id;
    public Date treatment_date;
    public String treatment_description;
    public String tooth_involved;
    public Currency procedure_amount;
    public Currency patient_charge; 
    public Currency insurance_charge; 
    public Currency total_charge; 
}
