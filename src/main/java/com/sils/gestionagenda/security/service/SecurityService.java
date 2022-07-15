package com.sils.gestionagenda.security.service;

import com.sils.gestionagenda.security.entities.AppRole;
import com.sils.gestionagenda.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
}
