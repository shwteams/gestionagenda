package com.sils.gestionagenda.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String titre;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd h:m:i")
    private Instant dateDebutRendezVous;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Instant dateFinRendezVous;

    private String description;
}
