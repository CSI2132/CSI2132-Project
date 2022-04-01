package csi2132.dentist.DentalOffice.repository;

import csi2132.dentist.DentalOffice.model.Patient;

import java.util.ArrayList;

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
                    bCryptPasswordEncoder.encode(patient.getPatient_password()),
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

    public int updatePatient(Patient patient) {
        String query =  "UPDATE Patient SET";
        ArrayList<Object> parameters = new ArrayList<Object>(10);
        if (patient.getUsername() != null) {
            query += " username = ?,";
            parameters.add(patient.getUsername());
        }

        System.out.println(patient.getPatient_password());
        if (patient.getPatient_password() != null) {
            query += " patient_password = ?,";
            parameters.add(bCryptPasswordEncoder.encode(patient.getPatient_password()));
        }

        if (patient.getPatient_address() != null) {
            query += " patient_address = ?,";
            parameters.add(patient.getPatient_address());
        }

        if (patient.getFirst_name() != null) {
            query += " first_name = ?,";
            parameters.add(patient.getFirst_name());
        }

        if (patient.getLast_name() != null) {
            query += " last_name = ?,";
            parameters.add(patient.getLast_name());
        }
        
        if (patient.getGender() != null) {
            query += " gender = ?,";
            parameters.add(patient.getGender());
        }

        if (patient.getInsurance() != null) {
            query += " insurance = ?,";
            parameters.add(patient.getInsurance());
        }
        
        if (patient.getSSN() != null) {
            query += " SSN = ?,";
            parameters.add(patient.getSSN());
        }

        if (patient.getEmail_address() != null) {
            query += " email_address = ?,";
            parameters.add(patient.getEmail_address());
        }
        
        if (patient.getDate_of_birth() != null) {
            query += " date_of_birth = ?,";
            parameters.add(patient.getDate_of_birth());
        }

        if (query.endsWith(",")) {
            query = query.substring(0, query.length() - 1);
        }

        if (query.endsWith("SET")) {
            // No data was provided to be updated
            return 0;
        }

        query += " WHERE user_id = ?;";
        parameters.add(patient.getUser_id());

        return jdbcTemplate.update(query, parameters.toArray());
    }
}
