package csi2132.dentist.DentalOffice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import csi2132.dentist.DentalOffice.model.Appointment;
import csi2132.dentist.DentalOffice.model.ProcedureType;

@Repository
public class AppointmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointment (patient_user_id, dentist_user_id, hygienist_user_id, appointment_date, start_time, end_time, appointment_type, appointment_status, assigned_room) VALUES (?, ?, ?, ?, ?::Time, ?::Time, ?::procedure_type_name_enum, ?, ?)";
        System.out.println(appointment.getStart_time());
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

    public List<Map<String, Object>> getAppointmentByPatientId(Integer user_id) {
        String sql = "SELECT aa.appointment_date, aa.start_time, aa.end_time, aa.assigned_room, aa.appointment_type, dentist.first_name, dentist.last_name, hygienist.first_name, hygienist.last_name "
                +
                "FROM Appointment AS aa " +
                "LEFT JOIN Dentist ON aa.dentist_user_id = Dentist.user_id " +
                "LEFT JOIN Hygienist ON aa.hygienist_user_id = Hygienist.user_id " +
                "WHERE aa.appointment_status = 'ACTIVE' AND aa.start_time > CURRENT_TIME AND aa.patient_user_id = ?";
        return jdbcTemplate.queryForList(sql, user_id);
    }

    public List<Map<String, Object>> getAppointmentByDentistId(Integer user_id) {
        String sql = "SELECT aa.appointment_date, aa.start_time, aa.end_time, aa.assigned_room, aa.appointment_type, dentist.first_name, dentist.last_name, hygienist.first_name, hygienist.last_name "
                +
                "FROM Appointment AS aa " +
                "LEFT JOIN Dentist ON aa.dentist_user_id = Dentist.user_id " +
                "LEFT JOIN Hygienist ON aa.hygienist_user_id = Hygienist.user_id " +
                "WHERE aa.appointment_status = 'ACTIVE' AND aa.start_time > CURRENT_TIME AND aa.dentist_user_id = ?";
        return jdbcTemplate.queryForList(sql, user_id);
    }

    // Medical history of patient records through patient_id
    public List<Map<String, Object>> getPatientRecord(Integer patient_user_id) {
        String sql = "SELECT * FROM record WHERE patient_user_id = ?";
        return jdbcTemplate.queryForList(sql, patient_user_id);
    }
}