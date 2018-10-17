package com.app.gofundme.controllers;

import com.app.gofundme.services.SingInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingInController {

    private SingInService singInService;

    @Autowired
    public SingInController(SingInService singInService) {
        this.singInService = singInService;
    }

    @PostMapping("/api/reg")
    public ResponseEntity registration(String email, String firstName,
                                       String lastName, String firstPass, String secondPass) {
        singInService.registrationOfUser(email, firstName, lastName, firstPass, secondPass);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity login(String email, String pass) {
        return ResponseEntity.ok(singInService.login(email, pass));
    }
}
