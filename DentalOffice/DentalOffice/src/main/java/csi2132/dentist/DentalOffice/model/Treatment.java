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
    private Currency procedure_amount;
    private Currency patient_charge; 
    private Currency insurance_charge; 
    private Currency total_charge; 
}
