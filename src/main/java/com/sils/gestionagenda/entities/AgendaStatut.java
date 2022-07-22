package com.sils.gestionagenda.entities;

public enum AgendaStatut {
    EMIS("émis"),
    MODIFIER("modifié"),
    EFFECTUER("effectué"),
    ANNULER("annulé"),
    CONFIRMER("confirmé");

    private String libelle;

    AgendaStatut(String libelle) {
        this.libelle = libelle;
    }
}
