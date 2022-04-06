package csi2132.dentist.DentalOffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import csi2132.dentist.DentalOffice.model.Dentist;
import csi2132.dentist.DentalOffice.repository.DentistRepository;

@Component
public class DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    public List<Map<String, Object>> getAllDentists() {
        return dentistRepository.getAllDentists();
    }

    /*public List<Map<String, Object>> getDentistsById(int branch_id) {
        return dentistRepository.getDentistsById(branch_id);
    }*/
}