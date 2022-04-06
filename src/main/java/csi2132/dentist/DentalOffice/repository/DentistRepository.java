package csi2132.dentist.DentalOffice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import csi2132.dentist.DentalOffice.model.Dentist;

@Repository
public class DentistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Map<String, Object>> getAllDentists() {
        String sql = "SELECT dentist.first_name, dentist.last_name, dentist.speciality, branch.branch_id, branch.city, branch.branch_address"
                +
                " FROM Dentist LEFT JOIN Branch ON Dentist.branch_id = Branch.branch_id;";
        return jdbcTemplate.queryForList(sql);
    }

    /*public List<Map<String, Object>> getDentistsById(int branch_id) {
        String sql = "SELECT dentist.first_name, dentist.last_name, dentist.speciality, branch.branch_id, branch.city, branch.branch_address"
                +
                " FROM Dentist LEFT JOIN Branch ON Dentist.branch_id = Branch.branch_id WHERE Branch.branch_id = ?;";
        return jdbcTemplate.queryForList(sql, branch_id);
<<<<<<< HEAD
    }

    public Integer addDentist(Dentist dentist){
        String sql = "INSERT INTO dentist (user_id, username, dentist_password, first_name, last_name, dentist_address, dentist_role, SSN, salary, branch_id, speciality) VALUES (?,?,?,?,?,?,?,?,?,?,?::procedure_type_name_enum)";
        Object[] parameters = new Object[]{
                "user_id",
                dentist.getUsername(),
                bCryptPasswordEncoder.encode(dentist.getPassword()),
                dentist.getFirst_name(),
                dentist.getLast_name(),
                dentist.getDentist_address(),
                dentist.getDentist_role(),
                dentist.getSSN(),
                dentist.getSalary(),
                dentist.getBranch_id(),
                dentist.getSpeciality()
        };

        return jdbcTemplate.update(sql, parameters);
    }
=======
    }*/
}