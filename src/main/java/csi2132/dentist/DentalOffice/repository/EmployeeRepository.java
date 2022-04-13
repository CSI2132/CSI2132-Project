package csi2132.dentist.DentalOffice.repository;

import csi2132.dentist.DentalOffice.RowMappers.EmployeeRowMapper;
import csi2132.dentist.DentalOffice.model.BranchManager;
import csi2132.dentist.DentalOffice.model.Dentist;
import csi2132.dentist.DentalOffice.model.Employee;
import csi2132.dentist.DentalOffice.model.Hygienist;
import csi2132.dentist.DentalOffice.model.Receptionist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Map<String, Object>> getAllEmployee() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.queryForList(sql);
    }

    public Employee getEmployee(Integer employeeId) {
        String sql = "SELECT * FROM employee WHERE user_id = ?";
        Object[] parameters = new Object[] { employeeId };
        return jdbcTemplate.queryForObject(sql, parameters, new EmployeeRowMapper());
    }

      public Integer addDentist(Dentist dentist) {
        int user_id = userRepository.addDentistAndReturnUserId(dentist);

        String sql = "INSERT INTO dentist (user_id, username, dentist_password, first_name, last_name, dentist_address, dentist_role, SSN, salary, branch_id, speciality) VALUES (?,?,?,?,?,?,?,?,?,?,?::procedure_type_name_enum)";
        Object[] parameters = new Object[] {
                user_id,
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

    public Integer addHygienist(Hygienist hygienist) {
        int user_id = userRepository.addHygienistAndReturnUserId(hygienist);

        String sql = "INSERT INTO hygienist (user_id, username, hygienist_password, first_name, last_name, hygienist_address, hygienist_role, SSN, salary, branch_id, seniority) VALUES (?,?,?,?,?,?,?,?,?,?,?::seniority_enum)";
        Object[] parameters = new Object[] {
                user_id,
                hygienist.getUsername(),
                bCryptPasswordEncoder.encode(hygienist.getPassword()),
                hygienist.getFirst_name(),
                hygienist.getLast_name(),
                hygienist.getHygienist_address(),
                hygienist.getHygienist_role(),
                hygienist.getSSN(),
                hygienist.getSalary(),
                hygienist.getBranch_id(),
                hygienist.getSeniority()
        };

        return jdbcTemplate.update(sql, parameters);
    }

    public Integer addReceptionist(Receptionist receptionist) {
        int user_id = userRepository.addReceptionistAndReturnUserId(receptionist);

        String sql = "INSERT INTO receptionist (user_id, username, receptionist_password, first_name, last_name, receptionist_address, SSN, salary, branch_id) VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] parameters = new Object[] {
                user_id,
                receptionist.getUsername(),
                bCryptPasswordEncoder.encode(receptionist.getPassword()),
                receptionist.getFirst_name(),
                receptionist.getLast_name(),
                receptionist.getReceptionist_address(),
                receptionist.getSSN(),
                receptionist.getSalary(),
                receptionist.getBranch_id()
        };

        return jdbcTemplate.update(sql, parameters);
    }

    public Integer addBranchManager(BranchManager branchManager) {
        int user_id = userRepository.addBranchManagerAndReturnUserId(branchManager);

        String sql = "INSERT INTO branchmanager (user_id, username, branch_manager_password, first_name, last_name, branch_manager_address, branch_manager_role, SSN, salary, branch_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Object[] parameters = new Object[] {
                user_id,
                branchManager.getUsername(),
                bCryptPasswordEncoder.encode(branchManager.getPassword()),
                branchManager.getFirst_name(),
                branchManager.getLast_name(),
                branchManager.getBranch_manager_address(),
                branchManager.getBranch_manager_role(),
                branchManager.getSSN(),
                branchManager.getSalary(),
                branchManager.getBranch_id()
        };

        return jdbcTemplate.update(sql, parameters);
    }
}
