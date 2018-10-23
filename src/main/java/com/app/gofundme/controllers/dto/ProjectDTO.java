package com.app.gofundme.controllers.dto;

import lombok.Data;

@Data
public class ProjectDTO {

    private String image;

    private String video;

    private String title;

    private Integer daysBetween;

    private String shortDescription;
}
