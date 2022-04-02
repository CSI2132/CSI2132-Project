package csi2132.dentist.DentalOffice.repository;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import csi2132.dentist.DentalOffice.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @return success
     */
    public UserDetails getDentistByBranchId(Integer branchId) {
        return null;
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM User WHERE username = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(query, username);

            return new User(result.getInt("user_id"), result.getString("username"), result.getString("password"));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User getUserByUserId(Integer userId) {
        String query = "SELECT * FROM User WHERE user_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(query, userId);

            return new User(result.getInt("user_id"), result.getString("username"), result.getString("password"));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
}