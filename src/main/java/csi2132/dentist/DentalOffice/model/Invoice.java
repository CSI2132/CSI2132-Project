package csi2132.dentist.DentalOffice.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;

public class Invoice {
    private Integer invoice_id;
    private Date date_of_issue;
    private String contact_email_address;
    private Currency patient_charge;
    private Currency insurance_charge;
    private Currency total_fee_charge;
    private BigDecimal discount;
    private boolean penalty_absent; 
    private Currency patient_amount;
    private Currency insurance_amount;
    private Currency total_amount;
    private String payment_type;
    private String patient_user_id;
    private String receptionist_user_id;
    private Integer appointment_id;
}
