package csi2132.dentist.DentalOffice.controller;

import csi2132.dentist.DentalOffice.model.Patient;
import csi2132.dentist.DentalOffice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @PutMapping(value = "/editPatient/{patientId}", produces = "application/json")  //{patientId}
    public ResponseEntity<?> updatePatient(@PathVariable("patientId") Integer patientId, @RequestBody Patient patient ) { //@PathVariable("patientId") String patientId,
        if (patientService.updatePatient(patient, patientId) > 0) {
            System.out.println("Successfully updated patient");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            System.out.println("Error updated patient");
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllPatient", produces = "application/json")
    public List<Map<String, Object>> getAllPatient(){
        List<Map<String, Object>> patient = patientService.getAllPatient();
        return patient;
    }

    //-- Input Validation --
    @GetMapping(value = "/getByUsername/{patientUsername}", produces = "application/json")
    public Boolean getPatientByUsername(@PathVariable("patientUsername") String patientUsername){
        Boolean patientCheckUsername = patientService.patientByUsername(patientUsername);
        return patientCheckUsername;
    }

    @GetMapping(value = "/getByEmail/{patientEmail}", produces = "application/json")
    public Boolean getPatientByEmail(@PathVariable("patientEmail") String patientEmail){
        Boolean patientCheckEmail = patientService.getPatientByEmail(patientEmail);
        return patientCheckEmail;
    }

    @GetMapping(value = "/getBySSN/{patientSSN}", produces = "application/json")
    public Boolean getPatientBySSN(@PathVariable("patientSSN") String patientSSN){
        Boolean patientCheckSSN = patientService.getPatientBySSN(patientSSN);
        return patientCheckSSN;
    }

} 