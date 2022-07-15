package com.sils.gestionagenda.controller;

import com.sils.gestionagenda.repositories.AgendaRepository;
import org.springframework.stereotype.Controller;

@Controller
public class AgendaController {
    private AgendaRepository agendaRepository;

    public AgendaController(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

}
