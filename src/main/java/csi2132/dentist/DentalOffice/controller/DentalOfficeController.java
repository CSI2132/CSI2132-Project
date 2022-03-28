package csi2132.dentist.DentalOffice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import csi2132.dentist.DentalOffice.repository.DentalOfficeRepository;

// @RestController
public class DentalOfficeController {

    //-- Instance Variables --
    @Autowired
    private final DentalOfficeRepository dentalOfficeRepo;
    
    //-- Constructor --
    DentalOfficeController(DentalOfficeRepository repo){
        this.dentalOfficeRepo = repo;
    }


    //-- Mappings/Routing --
    //@GetMapping("/employees")
    //List<Employee> allEmployees(){
        //return dentalOfficeRepo.findAll();
    // }

    //@PostMapping("/employees")
    //@PutMapping("/employees/{id}")
    //@DeleteMapping("/employees/{id}")


}
