package csi2132.dentist.DentalOffice.controller;

import csi2132.dentist.DentalOffice.model.Patient;
import csi2132.dentist.DentalOffice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public void addPatient(Patient patient) {
        patientService.addPatient(patient);
    }
}
