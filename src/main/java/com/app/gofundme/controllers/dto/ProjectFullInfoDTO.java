package com.app.gofundme.controllers.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectFullInfoDTO {

    private Long id;

    private Long image;

    private Long video;

    private String title;

    private String shortDescription;

    private HistoryDTO history;

    private Integer daysBetween;

    private Double currentSum;

    private Double goal;

    private Integer participantsCount;

    private List<ContributorDTO> contributors;

}
