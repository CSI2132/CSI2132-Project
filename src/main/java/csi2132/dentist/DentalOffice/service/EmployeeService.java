package csi2132.dentist.DentalOffice.service;

import csi2132.dentist.DentalOffice.model.Employee;
import csi2132.dentist.DentalOffice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Map<String, Object>> getAllEmployee(){
        return employeeRepository.getAllEmployee();
    }

    public List<Map<String, Object>> getEmployee(Integer user_id, String employeeType){
        return employeeRepository.getEmployee(user_id, employeeType);
    }

    public Integer createEmployee(Employee employee){
        return employeeRepository.createEmployee(employee);
    }
}