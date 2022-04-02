package csi2132.dentist.DentalOffice.repository;

import csi2132.dentist.DentalOffice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllEmployee() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getEmployee(Integer user_id, String employeeType) {
        String sql = "SELECT * FROM employee"; //Not sure how to use input parameters in the sql string
        return jdbcTemplate.queryForList(sql);
    }

    public int createEmployee(Employee employee){
        String sql = "INSERT INTO employee (user_id, employee_role) VALUES (?, ?::employee_role_enum)";
        return jdbcTemplate.update(sql, employee.getUser_Id(), employee.getEmployee_role());
    }
}
