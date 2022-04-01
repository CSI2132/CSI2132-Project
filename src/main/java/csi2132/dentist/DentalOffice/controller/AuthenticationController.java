package csi2132.dentist.DentalOffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {


  @PostMapping("/login")
    public String checkLogin(){
            // return(new ModelAndView("testTemplate"));
            return "redirect:index1";
    }

    @GetMapping("/test")
    public String index(){
        return "test";
    }
}