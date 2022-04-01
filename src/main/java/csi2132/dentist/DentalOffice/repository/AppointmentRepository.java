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
        return jdbcTemplate.update(sql, appointment.getPatient_user_id(), appointment.getDentist_user_id(), appointment.getHygienist_user_id(), appointment.getAppointment_date(), appointment.getStart_time(), appointment.getEnd_time(), appointment.getAppointment_type(), appointment.getAppointment_status(), appointment.getAssigned_room());
    }

    // ProcedureType Calls
    public List<Map<String, Object>> getProcedureType(){
        String sql = "SELECT * FROM ProcedureType";
        return jdbcTemplate.queryForList(sql);
    }
    public Integer createProcedureType(ProcedureType procedureType){
        String sql = "INSERT INTO proceduretype (procedure_id, procedure_type_name, procedure_type_description) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, procedureType.getProcedure_id(), procedureType.getProcedure_type_name(), procedureType.getProcedure_type_description());
    }
}