package com.app.gofundme.controllers.dto;

import lombok.Data;

@Data
public class ProjectShortDTO {

    private Long id;

    private String image;

    private String title;

    private Double goal;

    private Double currentSum;

    private Integer daysBetween;


}
