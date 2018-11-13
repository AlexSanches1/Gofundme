package com.app.gofundme.controllers.request_dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestCreateProjectDTO {

    private String title;

    private String shortDescription;

    private Date startDate;

    private Date endDate;
}
