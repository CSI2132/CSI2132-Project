package csi2132.dentist.DentalOffice.service;

import csi2132.dentist.DentalOffice.model.*;
import csi2132.dentist.DentalOffice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Map<String, Object>> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }

    public Employee getEmployee(Integer employeeId) {
        return employeeRepository.getEmployee(employeeId);
    }

    // Add dentist
    public Integer addDentist(Dentist dentist) {
        return employeeRepository.addDentist(dentist);
    }

    // Add hygienist
    public Integer addHygienist(Hygienist hygienist) {
        return employeeRepository.addHygienist(hygienist);
    }

    // Add receptionist
    public Integer addReceptionist(Receptionist receptionist) {
        return employeeRepository.addReceptionist(receptionist);
    }

    // Add branch manager
    public Integer addBranchManager(BranchManager branchManager) {
        return employeeRepository.addBranchManager(branchManager);
    }

}