package csi2132.dentist.DentalOffice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import csi2132.dentist.DentalOffice.dto.TreatmentRecord;
import csi2132.dentist.DentalOffice.model.Dentist;
import csi2132.dentist.DentalOffice.model.Treatment;

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

    public int addTreatment(Treatment treatment){

        String sql = "insert into treatment(appointment_type,treatment_type,medication,symptoms,tooth,comments,appointment_id, treatment_date,treatment_description,tooth_involved,procedure_amount,patient_charge,insurance_charge,total_charge ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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

        return jdbcTemplate.update(sql, parameters);


    }

    public Integer addRecord(Record record){
    
        String sql = "";
        Object[] parameters = new Object[]{};
        return jdbcTemplate.update(sql, parameters);

    }

}