package csi2132.dentist.DentalOffice.service;

import csi2132.dentist.DentalOffice.dto.UserLogin;
import csi2132.dentist.DentalOffice.model.User;
import csi2132.dentist.DentalOffice.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public boolean verifyCredentials(UserLogin login, User dbCreds){
        if(dbCreds == null || !login.username.equals(dbCreds.getUsername())) return false;
        System.out.println(dbCreds.getPassword());
        return bCryptPasswordEncoder.matches(login.password, dbCreds.getPassword());
    } 

    public Integer getUserId(UserLogin login) {
        return userRepository.getUserId(login);
    }

    public List<Map<String, Object>> getAllPatientId() { return userRepository.getAllPatientId();}


}