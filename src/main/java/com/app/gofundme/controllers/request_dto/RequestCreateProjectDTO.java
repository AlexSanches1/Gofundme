package com.app.gofundme.controllers.request_dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestCreateProjectDTO {

    private String image;

    private String video;

    private String title;

    private Date startDate;

    private Date endDate;

    private String shortDescription;


}
