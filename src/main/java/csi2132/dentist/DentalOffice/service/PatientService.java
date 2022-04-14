package csi2132.dentist.DentalOffice.service;

import csi2132.dentist.DentalOffice.model.Patient;
import csi2132.dentist.DentalOffice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public int addPatient(Patient patient) {
        return patientRepository.addPatient(patient);
    }

    public int updatePatient(Patient patient, Integer patientId) {return patientRepository.updatePatient(patient, patientId);}

    public List<Map<String, Object>> getAllPatient(){
        return patientRepository.getAllPatient();
    }

    public Boolean patientByUsername(String patientUsername){return patientRepository.getPatientByUsername(patientUsername);}
    public Boolean getPatientByEmail(String patientEmail){return patientRepository.getPatientByEmail(patientEmail);}
    public Boolean getPatientBySSN(String patientSSN){return patientRepository.getPatientBySSN(patientSSN);}
}
