package csi2132.dentist.DentalOffice.service;

import csi2132.dentist.DentalOffice.dto.UserLogin;
import csi2132.dentist.DentalOffice.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
	private static BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean verifyCredentials(UserLogin login, User dbCreds){
        if(dbCreds == null || !login.username.equals(dbCreds.getUsername())) return false;
        System.out.println(dbCreds.getPassword());
        return bCryptPasswordEncoder.matches(login.password, dbCreds.getPassword());
    } 


}