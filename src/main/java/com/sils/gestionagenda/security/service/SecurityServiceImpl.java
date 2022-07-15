package com.sils.gestionagenda.security.service;

import com.sils.gestionagenda.security.entities.AppRole;
import com.sils.gestionagenda.security.entities.AppUser;
import com.sils.gestionagenda.security.repositories.AppRoleRepository;
import com.sils.gestionagenda.security.repositories.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public SecurityServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)){
            throw new RuntimeException("Les mots de passe ne sont pas conforme.");
        }
        String hashesPassword = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashesPassword);
        appUser.setActive(true);
        AppUser appUserSaved = appUserRepository.save(appUser);
        return appUserSaved;
    }

    @Transactional
    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole!=null){
            throw new RuntimeException("le role "+roleName+" existe.");
        }
        else{
            appRole = new AppRole();
            appRole.setRoleName(roleName);
            appRole.setDescription(description);
            AppRole appRoleSaved = appRoleRepository.save(appRole);
            return appRoleSaved;
        }
    }

    @Transactional
    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw new RuntimeException("Utilisateur non trouvé.");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole == null) throw new RuntimeException("Role non trouvé.");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw new RuntimeException("Utilisateur non trouvé.");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole == null) throw new RuntimeException("Role non trouvé.");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
