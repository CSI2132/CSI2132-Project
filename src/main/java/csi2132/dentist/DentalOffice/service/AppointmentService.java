package csi2132.dentist.DentalOffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import csi2132.dentist.DentalOffice.model.Appointment;
import csi2132.dentist.DentalOffice.model.ProcedureType;
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

    public List<Map<String, Object>> getAppointmentByPatientId(int user_id) {
        return appointmentRepository.getAppointmentByPatientId(user_id);
    }

    public List<Map<String, Object>> getAppointmentByDentistId(int user_id) {
        return appointmentRepository.getAppointmentByDentistId(user_id);
    }
}