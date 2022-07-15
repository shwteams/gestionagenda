package com.sils.gestionagenda.controller;

import com.sils.gestionagenda.entities.TypeAgenda;
import com.sils.gestionagenda.repositories.TypeAgendaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TypeAgendaController {
    private TypeAgendaRepository typeAgendaRepository;

    public TypeAgendaController(TypeAgendaRepository typeAgendaRepository) {
        this.typeAgendaRepository = typeAgendaRepository;
    }
    @GetMapping(path = "/")
    public String index(){
        return "redirect:/user/home";
    }
    @GetMapping(path = "/home")
    public String home(){
        return "redirect:/user/home";
    }
    @GetMapping(path = "/user/home")
    public String homePage(){
        return "home";
    }
    @GetMapping(path = "/user/index")
    public String typesAgenda(Model model, @RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "5") int size, @RequestParam(name="rechercher", defaultValue = "") String rechercher){
        Page<TypeAgenda> pageTypeAgenda = typeAgendaRepository.findByLibelleContains(rechercher,PageRequest.of(page, size));
        model.addAttribute("listTypesAgenda", pageTypeAgenda.getContent());
        model.addAttribute("pages", new int[pageTypeAgenda.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("valeurRechercher", rechercher);
        return "typesagenda";
    }

    @GetMapping(path = "/admin/delete")
    public String delete(Long id
            , @RequestParam(name="rechercher", defaultValue = "") String rechercher
            , @RequestParam(name="page", defaultValue = "0") int page){
        typeAgendaRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&rechercher="+rechercher;
    }

    @GetMapping("/admin/formTypeAgenda")
    public String formTypeAgenda(Model model){
        model.addAttribute("typeAgenda", new TypeAgenda());
        return "formTypeAgenda";
    }

    @PostMapping(path = "/admin/saveTypeAgenda")
    public String saveTypeAgenda(Model model, @Valid TypeAgenda typeAgenda, BindingResult bindingResult
            , @RequestParam(name="page", defaultValue = "0") int page
            , @RequestParam(name="rechercher", defaultValue = "") String rechercher){
        if(bindingResult.hasErrors()){
            if(typeAgenda.getId()==null){
                return "formTypeAgenda";
            }
            else{
                return "editeTypeAgenda?page="+page+"&rechercher="+rechercher;
            }
        }
        //System.out.println(typeAgenda);
        typeAgendaRepository.save(typeAgenda);  //min: 50:21 https://www.youtube.com/watch?v=eoBE745lDE0
        //return "formTypeAgenda";
        return "redirect:/user/index?page="+page+"&rechercher="+rechercher;
    }
    @GetMapping(path = "/admin/editeTypeAgenda")
    public String editeTypeAgenda(Model model, Long id, String rechercher, int page){

        TypeAgenda typeAgenda = typeAgendaRepository.findById(id).orElse(null);
        if(typeAgenda == null){
            throw new RuntimeException("Type agenda introuvable");
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("valeurRechercher", rechercher);
        model.addAttribute("typeAgenda", typeAgenda);
        return "editeTypeAgenda";
    }
}
