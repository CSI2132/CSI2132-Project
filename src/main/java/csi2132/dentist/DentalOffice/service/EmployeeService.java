package csi2132.dentist.DentalOffice.service;

import csi2132.dentist.DentalOffice.model.BranchManager;
import csi2132.dentist.DentalOffice.model.Dentist;
import csi2132.dentist.DentalOffice.model.Hygienist;
import csi2132.dentist.DentalOffice.model.Receptionist;
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

    public List<Map<String, Object>> getEmployee(Integer user_id, String employeeType) {
        return employeeRepository.getEmployee(user_id, employeeType);
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