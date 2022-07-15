package com.sils.gestionagenda.entities;

public enum AgendaStatut {
    EMIS("émis"),
    MODIFIER("modifié"),
    EFFECTUE("effectué"),
    ANNULE("annulé"),
    CONFIRME("confirmé");

    private String libelle;

    AgendaStatut(String libelle) {
        this.libelle = libelle;
    }
}
