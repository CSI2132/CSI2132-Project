package csi2132.dentist.DentalOffice.dto;

public class UserLogin{

    public String username;
    public String password;

    public UserLogin(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}