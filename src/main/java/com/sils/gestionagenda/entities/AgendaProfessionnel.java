package com.sils.gestionagenda.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class AgendaProfessionnel extends Agenda{
    private String document;
}
