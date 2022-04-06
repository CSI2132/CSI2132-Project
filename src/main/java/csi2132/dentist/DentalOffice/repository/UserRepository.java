package csi2132.dentist.DentalOffice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import csi2132.dentist.DentalOffice.model.BranchManager;
import csi2132.dentist.DentalOffice.model.Dentist;
import csi2132.dentist.DentalOffice.model.Employee;
import csi2132.dentist.DentalOffice.model.Hygienist;
import csi2132.dentist.DentalOffice.model.Patient;
import csi2132.dentist.DentalOffice.model.Receptionist;

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

        Object[] params = { patient.getUsername(), bCryptPasswordEncoder.encode(patient.getPassword()) };
        /* Object[] params = { patient.getUsername(), patient.getPassword() }; */
        jdbcTemplate.update(sql, params);

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql2, username);
        if (rs.next()) {
            return rs.getInt("user_id");
        } else {
            return -1;
        }
    }

    public int addDentistAndReturnUserId(Dentist dentist) {

        // Add user first
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
        String username = dentist.getUsername();

        // get user id based on username
        String sql2 = "SELECT user_id FROM Users WHERE username = ?";

        Object[] params = { dentist.getUsername(), bCryptPasswordEncoder.encode(dentist.getPassword()) };
        /* Object[] params = { employee.getUsername(), employee.getPassword() }; */
        jdbcTemplate.update(sql, params);

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql2, username);

        // Add dentist to employee table
        String sql3 = "INSERT INTO Employee (user_id, employee_role) VALUES (?, ?)";

        int user_id = -1;

        if (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        jdbcTemplate.update(sql3, user_id, "DENTIST");

        return user_id;
    }

    public int addHygienistAndReturnUserId(Hygienist hygienist) {
        // Add user first
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
        String username = hygienist.getUsername();

        // get user id based on username
        String sql2 = "SELECT user_id FROM Users WHERE username = ?";

        Object[] params = { hygienist.getUsername(), bCryptPasswordEncoder.encode(hygienist.getPassword()) };
        jdbcTemplate.update(sql, params);

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql2, username);

        // Add hygienist to employee table
        String sql3 = "INSERT INTO Employee (user_id, employee_role) VALUES (?, ?)";

        int user_id = -1;

        if (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        jdbcTemplate.update(sql3, user_id, "HYGIENIST");

        return user_id;
    }

    public int addReceptionistAndReturnUserId(Receptionist receptionist) {
        // Add user first
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
        String username = receptionist.getUsername();

        // get user id based on username
        String sql2 = "SELECT user_id FROM Users WHERE username = ?";

        Object[] params = { receptionist.getUsername(), bCryptPasswordEncoder.encode(receptionist.getPassword()) };
        jdbcTemplate.update(sql, params);

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql2, username);

        // Add receptionist to employee table
        String sql3 = "INSERT INTO Employee (user_id, employee_role) VALUES (?, ?)";

        int user_id = -1;

        if (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        jdbcTemplate.update(sql3, user_id, "RECEPTIONIST");

        return user_id;
    }

    public int addBranchManagerAndReturnUserId(BranchManager branchManager) {
        // Add user first
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
        String username = branchManager.getUsername();

        // get user id based on username
        String sql2 = "SELECT user_id FROM Users WHERE username = ?";

        Object[] params = { branchManager.getUsername(), bCryptPasswordEncoder.encode(branchManager.getPassword()) };
        jdbcTemplate.update(sql, params);

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql2, username);

        // Add branch manager to employee table
        String sql3 = "INSERT INTO Employee (user_id, employee_role) VALUES (?, ?)";

        int user_id = -1;

        if (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        jdbcTemplate.update(sql3, user_id, "BRANCH_MANAGER");

        return user_id;
    }
}