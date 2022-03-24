package csi2132.dentist.DentalOffice.controller;

import csi2132.dentist.DentalOffice.model.Patient;
import csi2132.dentist.DentalOffice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        if (patientService.addPatient(patient) > 0) {
            System.out.println("Successfully added patient");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            System.out.println("Error adding patient");
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
