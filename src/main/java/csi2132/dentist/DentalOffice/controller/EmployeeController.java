package csi2132.dentist.DentalOffice.controller;

import csi2132.dentist.DentalOffice.model.Branch;
import csi2132.dentist.DentalOffice.model.BranchManager;
import csi2132.dentist.DentalOffice.model.Dentist;
import csi2132.dentist.DentalOffice.model.EmployeeAccesses;
import csi2132.dentist.DentalOffice.model.Hygienist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    // @Autowired
    // private EmployeeService employeeService;

    /*
    - [GET] All dentists at a branch {branchID}
    */
    // @GetMapping(value= "/", produces = "application/json")
    public ResponseEntity<?> listOfDentistsAtBranch (@RequestBody Branch branchId) {
        // if (employeeService.listOfDentistsAtBranch(branchId) != null) {
        //     System.out.println("Successfully obtained list of dentists at branch: [" + branchId + "]");
        //     return new ResponseEntity<>("", HttpStatus.OK);
        // }
        // else {
        //     System.out.println("Error obtaining list of dentists at branch");
        //     return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        // }

        return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR); //For now
    }


    /*
    - [POST] Add new employee
    */
    // @PostMapping(value = "/", produces = "application/json")
    // public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
    //     if (employeeService.addEmployee(employee) > 0) {
    //         System.out.println("Successfully added employee");
    //         return new ResponseEntity<>("", HttpStatus.OK);
    //     } else {
    //         System.out.println("Error adding employee");
    //         return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    

}