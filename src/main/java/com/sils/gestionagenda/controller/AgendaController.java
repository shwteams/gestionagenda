package com.sils.gestionagenda.controller;

import com.sils.gestionagenda.entities.Agenda;
import com.sils.gestionagenda.repositories.AgendaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AgendaController {
    private AgendaRepository agendaRepository;

    public AgendaController(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @GetMapping(path = "/user/agenda")
    public String typesAgenda(Model model, @RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "5") int size, @RequestParam(name="rechercher", defaultValue = "") String rechercher){
        Page<Agenda> pageTypeAgenda = agendaRepository.findAgendaByTitreContains(rechercher, PageRequest.of(page, size));
        model.addAttribute("listAgendas", pageTypeAgenda.getContent());
        model.addAttribute("pages", new int[pageTypeAgenda.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("valeurRechercher", rechercher);
        return "calendrier";
    }

}
