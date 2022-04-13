package csi2132.dentist.DentalOffice.RowMappers;

import csi2132.dentist.DentalOffice.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

        Employee employee = new Employee();

        employee.setUser_Id(rs.getInt("user_id"));
        employee.setEmployee_role(rs.getString("employee_role"));

        // Essentially dummy RowMapper, only returns the employee ID and employee type.
        // Need to make this work for all 4 cases, or make it per employee type

        return employee;
    }
}
