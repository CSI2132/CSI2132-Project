package csi2132.dentist.DentalOffice.controller;


import csi2132.dentist.DentalOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/allPatientId", produces = "application/json")
    public List<Map<String, Object>> getAllPatientId() {
        List<Map<String,Object>> users = userService.getAllPatientId();
        return userService.getAllPatientId();
    }
}
