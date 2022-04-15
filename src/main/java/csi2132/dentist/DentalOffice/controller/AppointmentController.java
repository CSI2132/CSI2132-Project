package csi2132.dentist.DentalOffice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csi2132.dentist.DentalOffice.model.Appointment;
import csi2132.dentist.DentalOffice.model.ProcedureType;
import csi2132.dentist.DentalOffice.model.Record;
import csi2132.dentist.DentalOffice.model.Treatment;
import csi2132.dentist.DentalOffice.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /*
    */
    @PostMapping(value = "/setAppointment", produces = "application/json")
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
     * - This endpoint has been commented out as POST should not be available to
     * users
     */
    // @PostMapping(value = "/addProcedure", produces = "application/json")
    // public ResponseEntity<?> addProcedures(@RequestBody ProcedureType
    // procedureType) {
    // return
    // ResponseEntity.ok(appointmentService.createProcedureType(procedureType));
    // }

    // - Upcoming appointments for patient {patientID}, dentist
    @GetMapping(value = "/getAppointmentByPatientId/{user_id}", produces = "application/json")
    public List<Map<String, Object>> getAppointmentByPatientId(@PathVariable Integer user_id) {
        return appointmentService.getAppointmentByPatientId(user_id);
    }

    // - Upcoming appointments for dentist {dentistID}
    @GetMapping(value = "/getAppointmentByDentistOrHygienistId/{user_id}", produces = "application/json")
    public List<Map<String, Object>> getAppointmentByDentistOrHygienistId(@PathVariable Integer user_id) {
        List<Map<String, Object>> result = appointmentService.getAppointmentByDentistId(user_id);
        if (result.size() > 0) {
            return result;
        } else {
            return appointmentService.getAppointmentByHygienistId(user_id);
        }
    }

    // - Medical history of patient records through patient_id
    @GetMapping(value = "/getPatientRecord/{patient_user_id}", produces = "application/json")
    public List<Map<String, Object>> getPatientRecord(@PathVariable Integer patient_user_id) {
        return appointmentService.getPatientRecord(patient_user_id);
    }

    // // - Create treatment
    // @PostMapping(value = "/addTreatment", produces = "application/json")
    // public ResponseEntity<?> addTreatment(@RequestBody Treatment treatment) {
    //     return ResponseEntity.ok(appointmentService.createTreatment(treatment));
    // }

    // // - Create patient record
    // @PostMapping(value = "/addPatientRecord", produces = "application/json")
    // public ResponseEntity<?> addPatientRecord(@RequestBody Record record) {
    //     return ResponseEntity.ok(appointmentService.createPatientRecord(record));
    // }

    // // Get treatment_id from treatment_type
    // @GetMapping(value = "/getTreatmentId/{treatment_type}", produces = "application/json")
    // public Integer getTreatmentId(@PathVariable String treatment_type) {
    //     return appointmentService.getTreatmentId(treatment_type);
    // }

    // // Get treatment_id from appointment_id
    // @GetMapping(value = "/getTreatmentIdByAppointmentId/{appointment_id}", produces = "application/json")
    // public Integer getTreatmentIdByAppointmentId(@PathVariable Integer appointment_id) {
    //     return appointmentService.getTreatmentIdByAppointmentId(appointment_id);
    // }
}