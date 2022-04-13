package csi2132.dentist.DentalOffice.dto;

public class UserLogin{

    public String username;
    public String password;
    public Integer role;

    public UserLogin(String username, String password, Integer role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public Integer getRole(){
        return role;
    }
}