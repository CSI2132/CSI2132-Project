package csi2132.dentist.DentalOffice.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import csi2132.dentist.DentalOffice.model.Patient;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @return success
     */
    public UserDetails getDentistByBranchId(Integer branchId) {
        return null;
    }

    public int addUserAndReturnUserId(Patient patient) {
        // Add user first
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
        String username = patient.getUsername();

        // get user id based on username
        String sql2 = "SELECT user_id FROM Users WHERE username = ?";

/*        Object[] params = { patient.getUsername(), bCryptPasswordEncoder.encode(patient.getPassword()) };*/
        Object[] params = { patient.getUsername(), patient.getPassword() };
        jdbcTemplate.update(sql, params);

        int user_id = jdbcTemplate.queryForRowSet(sql2, username).getInt("user_id");

        return user_id;
    }
}