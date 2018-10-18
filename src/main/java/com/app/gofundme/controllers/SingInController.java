package com.app.gofundme.controllers;

import com.app.gofundme.controllers.request_dto.RequestLoginDTO;
import com.app.gofundme.controllers.request_dto.RequestRegistrDTO;
import com.app.gofundme.services.SingInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingInController {

    private SingInService singInService;

    @Autowired
    public SingInController(SingInService singInService) {
        this.singInService = singInService;
    }

    @PostMapping("/api/reg")
    public ResponseEntity registration(@RequestBody RequestRegistrDTO registrDTO) {
        singInService.registrationOfUser(registrDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody RequestLoginDTO loginDTO) {
        return ResponseEntity.ok(singInService.login(loginDTO));
    }

    @GetMapping("api/admin/hello")
    public String hello() {
        return "Hello World";
    }
}
