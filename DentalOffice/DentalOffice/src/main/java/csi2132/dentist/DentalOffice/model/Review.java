package csi2132.dentist.DentalOffice.model;

import java.security.Timestamp;

public class Review {
    public Integer patient_user_id; 
    public Integer branch_id; 
    public Integer employee_professionalism_score; 
    public Integer cleanliness_score;
    public Integer rating_score; 
    public Timestamp review_datetime;
    public String additional_comments;
}