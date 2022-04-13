package csi2132.dentist.DentalOffice.repository;

import csi2132.dentist.DentalOffice.RowMappers.PatientRowMapper;
import csi2132.dentist.DentalOffice.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserRepository userRepository;

    /**
     * @return success
     */
    public int addPatient(Patient patient) {

        int user_id = userRepository.addUserAndReturnUserId(patient);

        String query = "INSERT INTO Patient(user_id, username, patient_password, patient_address, first_name, last_name, gender, insurance, SSN, email_address, date_of_birth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
        Object[] parameters = new Object[] {
                user_id,
                patient.getUsername(),
                bCryptPasswordEncoder.encode(patient.getPassword()),
                patient.getPatient_address(),
                patient.getFirst_name(),
                patient.getLast_name(),
                patient.getGender(),
                patient.getInsurance(),
                patient.getSSN(),
                patient.getEmail_address(),
                patient.getDate_of_birth() };

        return jdbcTemplate.update(query, parameters);
    }

    public int updatePatient(Patient patient, Integer patientId) {
        String query = "UPDATE Patient SET";
        ArrayList<Object> parameters = new ArrayList<Object>(10);
        if (patient.getUsername() != null && !patient.getUsername().isEmpty()) {
            query += " username = ?,";
            parameters.add(patient.getUsername());
            userRepository.updatePatientInfoInUsersTable(patient, patientId);  //Updates in USERNAME table 
            // Update user table here as well
        }

        System.out.println(patient.getPassword());
        if (patient.getPassword() != null && !patient.getPassword().isEmpty()) {
            query += " patient_password = ?,";
            parameters.add(bCryptPasswordEncoder.encode(patient.getPassword()));
            userRepository.updatePatientInfoInUsersTable(patient, patientId);
        }

        if (patient.getPatient_address() != null && !patient.getPatient_address().isEmpty()) {
            query += " patient_address = ?,";
            parameters.add(patient.getPatient_address());
        }

        if (patient.getFirst_name() != null && !patient.getFirst_name().isEmpty()) {
            query += " first_name = ?,";
            parameters.add(patient.getFirst_name());
        }

        if (patient.getLast_name() != null && !patient.getLast_name().isEmpty()) {
            query += " last_name = ?,";
            parameters.add(patient.getLast_name());
        }

        if (patient.getGender() != null && !patient.getGender().isEmpty()) {
            query += " gender = ?,";
            parameters.add(patient.getGender());
        }

        if (patient.getInsurance() != null && !patient.getInsurance().isEmpty()) {
            query += " insurance = ?,";
            parameters.add(patient.getInsurance());
        }

        if (patient.getSSN() != null && !patient.getSSN().isEmpty()) {
            query += " SSN = ?,";
            parameters.add(patient.getSSN());
        }

        if (patient.getEmail_address() != null && !patient.getEmail_address().isEmpty()) {
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
        parameters.add(patientId); //(patient.getUserId());

        return jdbcTemplate.update(query, parameters.toArray());
    }

    public List<Map<String, Object>> getAllPatient(){
        String query = "SELECT * FROM Patient;";
        return jdbcTemplate.queryForList(query);
    }

    public Patient getPatient(Integer patientId) {
        String sql = "SELECT * FROM Patient WHERE user_id = ?;";
        Object[] param = new Object[] { patientId };
        return jdbcTemplate.queryForObject(sql, param, new PatientRowMapper());
    }
}