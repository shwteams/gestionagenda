package com.sils.gestionagenda.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping(path = "/403")
    public String notAuthorized(){
        return "/403";
    }

    /*@GetMapping(value = "/user/isConnected")
    public String getUSerConnected() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        throw new RuntimeException("Aucun utilisateur connecté");
    }*/
}
