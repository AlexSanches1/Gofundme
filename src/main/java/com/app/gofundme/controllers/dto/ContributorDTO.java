package com.app.gofundme.controllers.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContributorDTO {

    private String fullName;

    private Double sumDonation;

    private Date date;
}
