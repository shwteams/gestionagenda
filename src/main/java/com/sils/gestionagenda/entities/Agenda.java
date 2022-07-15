package com.sils.gestionagenda.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titre;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd h:m:i")
    private Instant dateDebutRendezVous;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Instant dateFinRendezVous;

    @ManyToOne
    private TypeAgenda typeAgenda;
}
