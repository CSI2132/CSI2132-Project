package csi2132.dentist.DentalOffice.controller;

import csi2132.dentist.DentalOffice.model.*;
import csi2132.dentist.DentalOffice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /*
     * - [GET] All dentists at a branch {branchID}
     */
    // @GetMapping(value= "/", produces = "application/json")
    public ResponseEntity<?> listOfDentistsAtBranch(@RequestBody Branch branchId) {
        // if (employeeService.listOfDentistsAtBranch(branchId) != null) {
        // System.out.println("Successfully obtained list of dentists at branch: [" +
        // branchId + "]");
        // return new ResponseEntity<>("", HttpStatus.OK);
        // }
        // else {
        // System.out.println("Error obtaining list of dentists at branch");
        // return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        // }

        return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR); // For now
    }

    /*
     * - [GET] Get employee table contents (Entire list)
     */
    @GetMapping(value = "/getAllEmployee", produces = "application/json")
    public List<Map<String, Object>> getAllEmployee() {
        List<Map<String, Object>> employee = employeeService.getAllEmployee();
        return employee;
    }

    @GetMapping(value = "/getEmployeeType/{employeeId}", produces = "application/json")
    public Employee getEmployee(@PathVariable("employeeId") Integer employeeId){
        Employee employeeJSON = employeeService.getEmployee(employeeId);
        return employeeJSON;
    }

    /*
     * - [GET] Get specific employee with ID and employee type
     */
    /*
     * @GetMapping(value = "/getEmployee", produces = "application/json")
     * public List<Map<String, Object>> getEmployee(Integer user_id, String
     * employeeType) {
     * List<Map<String, Object>> employeeFetch =
     * employeeService.getEmployee(user_id, employeeType);
     * return employeeFetch;
     * }
     */

    // Add dentist
    @PostMapping(value = "/addDentist", produces = "application/json")
    public ResponseEntity<?> addDentist(@RequestBody Dentist newDentist) {
        if (employeeService.addDentist(newDentist) != null) {
            System.out.println("Successfully added new dentist: [" + newDentist.getFirst_name() + " "
                    + newDentist.getLast_name() + "]");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            System.out.println("Error adding new dentist");
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add hygienist
    @PostMapping(value = "/addHygienist", produces = "application/json")
    public ResponseEntity<?> addHygienist(@RequestBody Hygienist newHygienist) {
        if (employeeService.addHygienist(newHygienist) != null) {
            System.out.println("Successfully added new hygienist: [" + newHygienist.getFirst_name() + " "
                    + newHygienist.getLast_name() + "]");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            System.out.println("Error adding new hygienist");
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add receptionist
    @PostMapping(value = "/addReceptionist", produces = "application/json")
    public ResponseEntity<?> addReceptionist(@RequestBody Receptionist newReceptionist) {
        if (employeeService.addReceptionist(newReceptionist) != null) {
            System.out.println("Successfully added new receptionist: [" + newReceptionist.getFirst_name() + " "
                    + newReceptionist.getLast_name() + "]");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            System.out.println("Error adding new receptionist");
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add branch manager
    @PostMapping(value = "/addBranchManager", produces = "application/json")
    public ResponseEntity<?> addBranchManager(@RequestBody BranchManager newBranchManager) {
        if (employeeService.addBranchManager(newBranchManager) != null) {
            System.out.println("Successfully added new branch manager: [" + newBranchManager.getFirst_name() + " "
                    + newBranchManager.getLast_name() + "]");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            System.out.println("Error adding new branch manager");
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}