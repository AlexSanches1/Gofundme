package com.app.gofundme.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String image;

    private String video;

    private String title;

    private String shortDescription;

    private Double currentSum;

    private Date startDate;

    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "history_id")
    private History history;

    private List<User> contributors;

}
