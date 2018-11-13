package com.app.gofundme.controllers.dto;

import lombok.Data;

@Data
public class ProjectDTO {

    private Long id;

    private String pathToImage;

    private String pathToVideo;

    private String title;

    private Integer daysBetween;

    private String shortDescription;
}
