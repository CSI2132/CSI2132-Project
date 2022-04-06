package csi2132.dentist.DentalOffice.controller;

import csi2132.dentist.DentalOffice.model.Dentist;
import csi2132.dentist.DentalOffice.service.DentistService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    /*@RequestMapping(value = "/{branch_id}", method = RequestMethod.GET)
    public List<Map<String, Object>> getDentistsById(@PathVariable int branch_id) {
        return dentistService.getDentistsById(branch_id);
<<<<<<< HEAD
    }

    @PostMapping(value = "/addDentist", produces = "application/json")
    public ResponseEntity<?> addDentist(@RequestBody Dentist dentist){
        return ResponseEntity.ok(dentistService.addDentist(dentist));
    }
=======
    }*/
>>>>>>> 975052e95ebef8083515de14c51eaea22f9e3afd
}