package csi2132.dentist.DentalOffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import csi2132.dentist.DentalOffice.dto.TreatmentRecord;
import csi2132.dentist.DentalOffice.model.Dentist;
import csi2132.dentist.DentalOffice.repository.DentistRepository;
import csi2132.dentist.DentalOffice.model.Record;

@Component
public class DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    public List<Map<String, Object>> getAllDentists() {
        return dentistRepository.getAllDentists();
    }

    /*
     * public List<Map<String, Object>> getDentistsById(int branch_id) {
     * return dentistRepository.getDentistsById(branch_id);
     * }
     */

    public int addTreatmentRecord(TreatmentRecord tr) {
        int val = 0;
        // if(tr.treatment.getAppointmentType() != null &&
        // tr.treatment.getTreatmentType() != null && tr.treatment.getMedication() !=
        // null && tr.treatment.getSymptoms() != null
        // && tr.treatment.getTooth() != null && tr.treatment.getTreatmentDate() != null
        // && tr.treatment.getToothInvolved() != null
        // ){
        val = dentistRepository.addTreatment(tr.treatment);

        // }
        // int val2 = dentistRepository.addRecord(tr.record);

        return val;
    }
}