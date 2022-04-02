package csi2132.dentist.DentalOffice.repository;

import csi2132.dentist.DentalOffice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createEmployee(Employee employee){
        String sql = "INSERT INTO employee (user_id, employee_role) VALUES (?, ?::employee_role_enum)";
        return jdbcTemplate.update(sql, employee.getUser_Id(), employee.getEmployee_role());
    }
}
