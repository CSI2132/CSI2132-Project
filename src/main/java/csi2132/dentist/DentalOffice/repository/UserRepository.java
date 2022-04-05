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
        String sql = "INSERT INTO \"user\" (username, password) VALUES (?, ?)";
        String username = patient.getUsername();

        // get user id based on username
        String sql2 = "SELECT user_id FROM \"user\" WHERE username = ?";
        int user_id = jdbcTemplate.queryForRowSet(sql2, username).getInt("user_id");

        Object[] params = { patient.getUsername(), bCryptPasswordEncoder.encode(patient.getPassword()) };
        jdbcTemplate.update(sql, params);

        return user_id;
    }
}