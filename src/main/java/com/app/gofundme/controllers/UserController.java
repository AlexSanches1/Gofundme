package com.app.gofundme.controllers;

import com.app.gofundme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/getId/{id}")
    public ResponseEntity getFullInfo(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getFullUserInfo(id));
    }
}
