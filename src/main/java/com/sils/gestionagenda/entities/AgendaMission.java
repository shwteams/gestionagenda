package com.sils.gestionagenda.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class AgendaMission extends Agenda{
    private String documentOrdreMission;
    private String longitude;
    private String latitude;
}
