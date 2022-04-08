package csi2132.dentist.DentalOffice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import csi2132.dentist.DentalOffice.repository.UserRepository;
import csi2132.dentist.DentalOffice.service.UserService;
import csi2132.dentist.DentalOffice.dto.UserLogin;
import csi2132.dentist.DentalOffice.model.*;

@Controller
public class AuthenticationController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  Map<String, Integer> userTokens = new HashMap<String, Integer>();

  @PostMapping("/login")
  public ResponseEntity<String> checkLogin(HttpServletResponse response, @RequestBody UserLogin login) {
    // todo: first verify
    User user = userRepository.getUserByUsername(login.username);

    if (userService.verifyCredentials(login, user)) {
      String token = Math.random() + "";
      userTokens.put(token, user.getUserId());

      response.addCookie(new Cookie("auth", token));
      return new ResponseEntity<>("", HttpStatus.OK); // For now

    } else {
      return new ResponseEntity<>("", HttpStatus.FORBIDDEN); // For now
    }
  }
}