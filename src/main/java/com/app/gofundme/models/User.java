package com.app.gofundme.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String secondName;

    @NotNull
    private String password;

    private String image;

    private Boolean isAdmin;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private List<Project> projects;
}
