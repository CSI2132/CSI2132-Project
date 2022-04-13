package csi2132.dentist.DentalOffice.dto;

public class LoginResponse {
    private Integer userId;

    public LoginResponse(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
