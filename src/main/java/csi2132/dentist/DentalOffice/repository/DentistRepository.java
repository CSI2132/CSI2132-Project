package csi2132.dentist.DentalOffice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import csi2132.dentist.DentalOffice.model.Dentist;

@Repository
public class DentistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllDentists() {
        String sql = "SELECT dentist.first_name, dentist.last_name, dentist.speciality, branch.branch_id, branch.city, branch.branch_address"
                +
                " FROM Dentist LEFT JOIN Branch ON Dentist.branch_id = Branch.branch_id;";
        return jdbcTemplate.queryForList(sql);
    }
}