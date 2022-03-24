package csi2132.dentist.DentalOffice.model;

import java.security.Timestamp;

public class Review {
    private Integer patient_user_id; 
    private Integer branch_id; 
    private Integer employee_professionalism_score; 
    private Integer cleanliness_score;
    private Integer rating_score; 
    private Timestamp review_datetime;
    private String additional_comments;
}