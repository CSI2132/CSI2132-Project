package csi2132.dentist.DentalOffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import csi2132.dentist.DentalOffice.dto.LoginResponse;
import csi2132.dentist.DentalOffice.dto.UserLogin;
import csi2132.dentist.DentalOffice.service.UserService;


@RestController
public class AuthenticationController {

  @Autowired
  private UserService userService;


  @PostMapping(value = "/login", produces = "application/json")
  public @ResponseBody ResponseEntity<?> checkLogin(@RequestBody UserLogin login) {
    Integer userId = userService.getUserId(login);

    if (userId != null) {
      return ResponseEntity.ok(new LoginResponse(userId));
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}