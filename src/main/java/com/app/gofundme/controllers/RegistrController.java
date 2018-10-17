package com.app.gofundme.controllers;

import com.app.gofundme.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrController {

    private RegistrationService registrationService;

    @Autowired
    public RegistrController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/api/reg")
    public ResponseEntity registration(String email, String firstName,
                                       String lastName, String firstPass, String secondPass) {
        registrationService.registrationOfUser(email, firstName, lastName, firstPass, secondPass);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
