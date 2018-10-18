package com.app.gofundme.controllers.request_dto;

import lombok.Data;

@Data
public class RequestRegistrDTO {

    private String email;

    private String firstName;

    private String secondName;

    private String firstPassword;

    private String secondPassword;
}
