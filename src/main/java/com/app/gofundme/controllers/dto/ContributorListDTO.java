package com.app.gofundme.controllers.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContributorListDTO {

    private String fullName;

    private Double sumDonation;

    private Date date;
}
