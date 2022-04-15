package csi2132.dentist.DentalOffice.controller;

import csi2132.dentist.DentalOffice.dto.TreatmentRecord;
import csi2132.dentist.DentalOffice.service.DentistService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Map<String, Object>> getAllDentists() {
        return dentistService.getAllDentists();
    }

    /*
     * @RequestMapping(value = "/{branch_id}", method = RequestMethod.GET)
     * public List<Map<String, Object>> getDentistsById(@PathVariable int branch_id)
     * {
     * return dentistService.getDentistsById(branch_id);
     * }
     */
    @PostMapping(value = "/addTreatmentInfo", consumes = "application/json")
    public Integer createTreatment(@RequestBody TreatmentRecord treatmentRec) {
        return dentistService.addTreatmentRecord(treatmentRec);
    }

}