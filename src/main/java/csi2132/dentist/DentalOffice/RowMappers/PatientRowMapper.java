package csi2132.dentist.DentalOffice.RowMappers;

import csi2132.dentist.DentalOffice.model.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {

        Patient patient = new Patient();

        patient.setUserId(rs.getInt("user_id"));
        patient.setUsername(rs.getString("username"));
        patient.setPassword(rs.getString("patient_password"));
        patient.setPatient_address(rs.getString("patient_address"));
        patient.setFirst_name(rs.getString("first_name"));
        patient.setLast_name(rs.getString("last_name"));
        patient.setGender(rs.getString("gender"));
        patient.setInsurance(rs.getString("insurance"));
        patient.setSSN(rs.getString("SSN"));
        patient.setEmail_address(rs.getString("email_address"));
        patient.setDate_of_birth(rs.getDate("date_of_birth"));

        return patient;

    }
}
