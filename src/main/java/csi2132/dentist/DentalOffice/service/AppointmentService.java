package csi2132.dentist.DentalOffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import csi2132.dentist.DentalOffice.model.Appointment;
import csi2132.dentist.DentalOffice.model.ProcedureType;
import csi2132.dentist.DentalOffice.model.Record;
import csi2132.dentist.DentalOffice.model.Treatment;
import csi2132.dentist.DentalOffice.repository.AppointmentRepository;

@Component
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public int createAppointment(Appointment patient) {
        return appointmentRepository.createAppointment(patient);
    }

    // ProcedureType Calls
    public List<Map<String, Object>> getProcedureType() {
        return appointmentRepository.getProcedureType();
    }

    public Integer createProcedureType(ProcedureType procedureType) {
        return appointmentRepository.createProcedureType(procedureType);
    }

    public List<Map<String, Object>> getAppointmentByPatientId(Integer user_id) {
        return appointmentRepository.getAppointmentByPatientId(user_id);
    }

    public List<Map<String, Object>> getAppointmentByDentistId(Integer user_id) {
        return appointmentRepository.getAppointmentByDentistId(user_id);
    }

    // Medical history of patient records through patient_id
    public List<Map<String, Object>> getPatientRecord(Integer patient_user_id) {
        return appointmentRepository.getPatientRecord(patient_user_id);
    }

    // // Create treatment
    // public Integer createTreatment(Treatment treatment) {
    //     return appointmentRepository.createTreatment(treatment);
    // }

    // // Create patient record
    // public Integer createPatientRecord(Record record) {
    //     return appointmentRepository.createPatientRecord(record);
    // }

    // // Get treatment_id from treatment_type
    // public Integer getTreatmentId(String treatment_type) {
    //     return appointmentRepository.getTreatmentId(treatment_type);
    // }

    // // Get treatment_id from appointment_id
    // public Integer getTreatmentIdByAppointmentId(Integer appointment_id) {
    //     return appointmentRepository.getTreatmentIdFromAppointment(appointment_id);
    // }
}