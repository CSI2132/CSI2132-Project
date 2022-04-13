package csi2132.dentist.DentalOffice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
// import record
import csi2132.dentist.DentalOffice.model.Record;

import csi2132.dentist.DentalOffice.model.Appointment;
import csi2132.dentist.DentalOffice.model.ProcedureType;
import csi2132.dentist.DentalOffice.model.Treatment;

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
                "WHERE aa.appointment_status = 'ACTIVE' AND aa.appointment_date > CURRENT_DATE AND aa.patient_user_id = ?";
        return jdbcTemplate.queryForList(sql, user_id);
    }

    public List<Map<String, Object>> getAppointmentByDentistId(Integer user_id) {
        String sql = "SELECT aa.appointment_date, aa.start_time, aa.end_time, aa.assigned_room, aa.appointment_type, dentist.first_name, dentist.last_name, hygienist.first_name, hygienist.last_name "
                +
                "FROM Appointment AS aa " +
                "LEFT JOIN Dentist ON aa.dentist_user_id = Dentist.user_id " +
                "LEFT JOIN Hygienist ON aa.hygienist_user_id = Hygienist.user_id " +
                "WHERE aa.appointment_status = 'ACTIVE' AND aa.dentist_user_id = ?";
        return jdbcTemplate.queryForList(sql, user_id);
    }

    // Medical history of patient records through patient_id
    public List<Map<String, Object>> getPatientRecord(Integer patient_user_id) {
        String sql = "SELECT * FROM record WHERE patient_user_id = ?";
        return jdbcTemplate.queryForList(sql, patient_user_id);
    }

    // Get treatment_id from treatment_type
    public Integer getTreatmentId(String treatment_type) {
        String sql = "SELECT treatment_id FROM treatment WHERE treatment_type = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, treatment_type);
    }

    // Get treatment_id from appointment_id in treatment table
    public Integer getTreatmentIdFromAppointment(Integer appointment_id) {
        String sql = "SELECT treatment_id FROM treatment WHERE appointment_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, appointment_id);
    }

    // Add treatment record from treatment model class
    public int createTreatment(Treatment treatment) {
        String sql = "INSERT INTO treatment (appointment_type, treatment_type, medication, symptoms, tooth, " +
                "comments, appointment_id, treatment_date, treatment_description, tooth_involved, procedure_amount, patient_charge, "
                +
                "insurance_charge, total_charge) VALUES (?::procedure_type_name_enum, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, treatment.getAppointment_type(), treatment.getTreatment_type(),
                treatment.getMedication(), treatment.getSymptoms(), treatment.getTooth(), treatment.getComments(),
                treatment.getAppointment_id(), treatment.getTreatment_date(), treatment.getTreatment_description(),
                treatment.getTooth_involved(), treatment.getProcedure_amount(), treatment.getPatient_charge(),
                treatment.getInsurance_charge(), treatment.getTotal_charge());
    }

    // Add patient record for a patient
    public int createPatientRecord(Record record) {
        String sql = "INSERT INTO record (progress_notes, patient_user_id, treatment_id)" +
                "VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, record.getProgress_notes(), record.getPatient_user_id(),
                record.getTreatment_id());
    }
}