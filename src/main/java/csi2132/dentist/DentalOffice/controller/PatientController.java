package csi2132.dentist.DentalOffice.controller;

import csi2132.dentist.DentalOffice.model.Patient;
import csi2132.dentist.DentalOffice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController extends EmployeeController {

    @Autowired
    private PatientService patientService;

    /*
    - [POST] Adding a new patient
    */
    @PostMapping(value = "/addPatient", produces = "application/json")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        if (patientService.addPatient(patient) > 0) {
            System.out.println("Successfully added patient");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            System.out.println("Error adding patient");
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    - [PUT] Edit patient info
    */
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient) {
        if (patientService.updatePatient(patient) > 0) {
            System.out.println("Successfully updated patient");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            System.out.println("Error updated patient");
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
