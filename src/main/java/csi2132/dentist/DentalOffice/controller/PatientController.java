package csi2132.dentist.DentalOffice.controller;

import csi2132.dentist.DentalOffice.model.Patient;
import csi2132.dentist.DentalOffice.service.AppointmentService;
import csi2132.dentist.DentalOffice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
public class PatientController extends EmployeeController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

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

    @GetMapping("/ui")
    public String patientUI(Model model) {
        // model.addAttribute("appointments", appointmentService.getUpcomingAppointment());
        model.addAttribute("appointments", appointmentService.getProcedureType());  //Using (getProcedureType) just to TEST
        return "patient";
    }

    // @GetMapping("/ui")
    // public ModelAndView patientUI(Model model) {
    //     ModelAndView modelAndView = new ModelAndView("patient");
    //     return modelAndView;
    // }
}
