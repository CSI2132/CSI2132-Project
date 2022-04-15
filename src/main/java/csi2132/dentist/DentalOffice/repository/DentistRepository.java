package csi2132.dentist.DentalOffice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import csi2132.dentist.DentalOffice.model.Treatment;
import csi2132.dentist.DentalOffice.model.Record;

@Repository
public class DentistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllDentists() {
        String sql = "SELECT dentist.first_name, dentist.last_name, dentist.speciality, branch.branch_id, branch.city, branch.branch_address"
                +
                " FROM Dentist LEFT JOIN Branch ON Dentist.branch_id = Branch.branch_id;";
        return jdbcTemplate.queryForList(sql);
    }

    public Integer addTreatment(Treatment treatment) {

        String sql = "insert into treatment(appointment_type,treatment_type,medication,symptoms,tooth,comments,appointment_id, treatment_date,treatment_description,tooth_involved,procedure_amount,patient_charge,insurance_charge,total_charge ) values (?::procedure_type_name_enum,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Object[] parameters = new Object[] {
                treatment.getAppointmentType(),
                treatment.getTreatmentType(),
                treatment.getMedication(),
                treatment.getSymptoms(),
                treatment.getTooth(),
                treatment.getComments(),
                treatment.getAppointmentId(),
                treatment.getTreatmentDate(),
                treatment.getTreatmentDescription(),
                treatment.getToothInvolved(),
                treatment.getProcedureAmount(),
                treatment.getPatientCharge(),
                treatment.getInsuranceCharge(),
                treatment.getTotalCharge()
        };

        jdbcTemplate.update(sql, parameters);

        String returnSql = "SELECT treatment_id FROM treatment WHERE treatment_id=(SELECT max(treatment_id) FROM treatment)";

        // return the id of the newly added treatment
        SqlRowSet rs = jdbcTemplate.queryForRowSet(returnSql);
        rs.next();
        return rs.getInt("treatment_id");
    }

    public Integer addRecord(Record record, Integer treatment_id) {
        String progressNotes = record.getProgressNotes();
        Integer patient_user_id = record.getPatientUserId();

        String sql = "insert into record VALUES(?,?,?)";
        Object[] parameters = new Object[] {
                record.getProgressNotes(),
                record.getPatientUserId(),
                treatment_id
        };
        return jdbcTemplate.update(sql, parameters);

    }

    public Map<String, Object> getTreatmentByAppointmentId(int appointment_id) {
        return null;
    }

}