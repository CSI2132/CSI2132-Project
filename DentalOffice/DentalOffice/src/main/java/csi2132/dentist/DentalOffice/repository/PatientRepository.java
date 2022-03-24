package csi2132.dentist.DentalOffice.repository;

import csi2132.dentist.DentalOffice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @return success
     */
    public int addPatient(Patient patient) {
        String query = "INSERT INTO Patient(username, patient_password, patient_address, first_name, last_name, gender, insurance, SSN, email_address, date_of_birth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
        Object[] parameters = new Object[] {
                    patient.getUsername(), 
                    "test", // bCryptPasswordEncoder.encode(patient.getPatient_password()), 
                    patient.getPatient_address(), 
                    patient.getFirst_name(), 
                    patient.getLast_name(), 
                    patient.getGender(), 
                    patient.getInsurance(), 
                    patient.getSSN(), 
                    patient.getEmail_address(), 
                    patient.getDate_of_birth()};
                
        return jdbcTemplate.update(query, parameters);
    }
}
