package csi2132.dentist.DentalOffice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import csi2132.dentist.DentalOffice.model.Appointment;
import csi2132.dentist.DentalOffice.model.ProcedureType;

@Repository
public class AppointmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointment (patient_user_id, dentist_user_id, hygienist_user_id, appointment_date, start_time, end_time, appointment_type, appointment_status, assigned_room) VALUES (?, ?, ?, ?, ?, ?, ?::procedure_type_name_enum, ?, ?)";
        return jdbcTemplate.update(sql, appointment.getPatient_user_id(), appointment.getDentist_user_id(),
                appointment.getHygienist_user_id(), appointment.getAppointment_date(), appointment.getStart_time(),
                appointment.getEnd_time(), appointment.getAppointment_type(), appointment.getAppointment_status(),
                appointment.getAssigned_room());
    }

    // ProcedureType Calls
    public List<Map<String, Object>> getProcedureType() {
        String sql = "SELECT * FROM ProcedureType";
        return jdbcTemplate.queryForList(sql);
    }

    public Integer createProcedureType(ProcedureType procedureType) {
        String sql = "INSERT INTO proceduretype (procedure_id, procedure_type_name, procedure_type_description) VALUES (?, ?::procedure_type_name_enum, ?)";
        return jdbcTemplate.update(sql, procedureType.getProcedure_id(), procedureType.getProcedure_type_name(),
                procedureType.getProcedure_type_description());
    }

    public List<Map<String, Object>> getAppointmentByPatientId(int user_id) {
        String sql = "SELECT a.appointment_date, a.start_time, a.end_time, a.assigned_room, a.appointment_type, dentist.first_name, dentist.last_name, hygienist.first_name, hygienist.last_name"
                +
                "FROM Appointment AS a LEFT JOIN Dentist ON a.user_id = Dentist.user_id LEFT JOIN Hygienist ON a.hygienist_user_id = Hygienist.user_id"
                +
                "WHERE appointment_status = 'ACTIVE' AND a.start_time > CURRENT_TIME WHERE patient.user_id = ?";
        return jdbcTemplate.queryForList(sql, user_id);
    }

    public List<Map<String, Object>> getAppointmentByDentistId(int user_id) {
        String sql = "SELECT a.appointment_date, a.start_time, a.end_time, a.assigned_room, a.appointment_type, patient.first_name, patient.last_name, hygienist.first_name, hygienist.last_name"
                +
                "FROM Appointment AS a LEFT JOIN Patient ON a.user_id = Patient.user_id LEFT JOIN Hygienist ON a.hygienist_user_id = Hygienist.user_id"
                +
                "WHERE appointment_status = 'ACTIVE' AND a.start_time > CURRENT_TIME WHERE dentist.user_id = ?";
        return jdbcTemplate.queryForList(sql, user_id);
    }
}