package csi2132.dentist.DentalOffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
// @RequestMapping("/")
public class AuthenticationController {

  // @PostMapping("/login")
  // public ModelAndView checkLogin() {
  //   // return(new ModelAndView("testTemplate"));
  //   return new ModelAndView("redirect:/index1");
  // }


  @PostMapping("/login")
  public String checkLogin(@RequestBody String login) {
    // return(new ModelAndView("testTemplate"));
    return "index1";
  }

//   @GetMapping("/index1")
//   public String index1() {
//     return "test";
//   }
}