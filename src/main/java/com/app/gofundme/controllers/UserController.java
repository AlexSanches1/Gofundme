package com.app.gofundme.controllers;

import com.app.gofundme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    public ResponseEntity getUserId(Long id){
      return   ResponseEntity.ok(userService.getUserId(id));
    }
}
