package csi2132.dentist.DentalOffice.service;

import csi2132.dentist.DentalOffice.model.Patient;
import csi2132.dentist.DentalOffice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public int addPatient(Patient patient) {
        return patientRepository.addPatient(patient);
    }

    public int updatePatient(Patient patient) {
        return patientRepository.updatePatient(patient);
    }
}
