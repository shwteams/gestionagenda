package com.sils.gestionagenda.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SuiviStatutAgenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private Instant dateDemande;
    private Instant dateAcceptation;
    private Instant dateDebutValidite;
    private Instant dateFinValidite;

    @ManyToOne
    private Agenda agenda;

    private AgendaStatut agendaStatut;
}
