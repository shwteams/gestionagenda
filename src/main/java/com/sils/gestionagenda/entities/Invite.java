package com.sils.gestionagenda.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String telephone;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;

    @ManyToOne
    private Agenda agenda;
}
