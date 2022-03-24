package csi2132.dentist.DentalOffice.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;

public class Invoice {
    public Integer invoice_id;
    public Date date_of_issue;
    public String contact_email_address;
    public Currency patient_charge;
    public Currency insurance_charge;
    public Currency total_fee_charge;
    public BigDecimal discount;
    public boolean penalty_absent; 
    public Currency patient_amount;
    public Currency insurance_amount;
    public Currency total_amount;
    public String payment_type;
    public String patient_user_id;
    public String receptionist_user_id;
    public Integer appointment_id;
}
