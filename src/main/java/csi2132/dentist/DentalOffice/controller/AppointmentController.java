package csi2132.dentist.DentalOffice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csi2132.dentist.DentalOffice.model.Appointment;
import csi2132.dentist.DentalOffice.model.ProcedureType;
import csi2132.dentist.DentalOffice.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /*
    */
    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }

    /*
     * - [GET] Check the types of procedures available
     */
    @GetMapping(value = "/getProcedure", produces = "application/json")
    public List<Map<String, Object>> getProcedureList() {
        List<Map<String, Object>> procedureList = appointmentService.getProcedureType();
        return procedureList;
    }

    /*
     * - [POST] Add a new procedure type
     */
    @PostMapping(value = "/addProcedure", produces = "application/json")
    public ResponseEntity<?> addProcedures(@RequestBody ProcedureType procedureType) {
        return ResponseEntity.ok(appointmentService.createProcedureType(procedureType));
    }

    // - Upcoming appointments for patient {patientID}, dentist
    @GetMapping(value = "/getAppointmentByPatientId/{user_id}", produces = "application/json")
    public List<Map<String, Object>> getAppointmentByPatientId(int user_id) {
        return appointmentService.getAppointmentByPatientId(user_id);
    }

    // - Upcoming appointments for dentist {dentistID}
    @GetMapping(value = "/getAppointmentByDentistId/{user_id}", produces = "application/json")
    public List<Map<String, Object>> getAppointmentByDentistId(int user_id) {
        return appointmentService.getAppointmentByDentistId(user_id);
    }

    // - Medical history of patient records through patient_id
    @GetMapping(value = "/getPatientRecord/{patient_user_id}", produces = "application/json")
    public List<Map<String, Object>> getPatientRecord(int patient_user_id) {
        return appointmentService.getPatientRecord(patient_user_id);
    }
}