package csi2132.dentist.DentalOffice.controller;

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
}