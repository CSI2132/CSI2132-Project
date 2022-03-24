package csi2132.dentist.DentalOffice.repository;

import csi2132.dentist.DentalOffice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addPatient(Patient patient) {
        String query = "INSERT INTO Patient(user_id, username, patient_password, patient_address, first_name, last_name, gender, insurance, SSN, email_address, date_of_birth) \n" +
                "VALUES (1, 'JohnDoe', 'doeJohnPswd', '123 Example Street', 'John', 'Doe', 'MALE', 'Allstate Insurance Inc.',12456789, 'john.doe@hotmail.com', '1999-01-31');\n" +
                "\n";
        int test = jdbcTemplate.update(query);
    }
}
