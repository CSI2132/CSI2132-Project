package csi2132.dentist.DentalOffice.repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import csi2132.dentist.DentalOffice.model.Employee;
import csi2132.dentist.DentalOffice.model.Patient;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.sql.RowSet;

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
/*        Object[] params = { patient.getUsername(), patient.getPassword() };*/
        jdbcTemplate.update(sql, params);

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql2, username);
        if(rs.next()){
            return rs.getInt("user_id");
        }else{
            return -1;
        }
/*        rs.getInt("user_id");*/


        /*return user_id;*/
    }

    public int addEmployeeAndReturnUserId(Employee employee) {

        // Add cases for employee type
        String employeeRole = employee.getEmployee_role();

        switch (employeeRole){
            case "DENTIST" :
                return 0;
            case "HYGIENIST" :
                return 0;
            case "RECEPTIONIST" :
                return 0;
            case "BRANCHMANAGER" :
                return 0;
            default:
                return 0;
        }
    }
}