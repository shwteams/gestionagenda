package com.sils.gestionagenda.security.service;

import com.sils.gestionagenda.security.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private SecurityService securityService;

    public UserDetailsServiceImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = securityService.loadUserByUsername(username);

        //Methode 1 avec la programmation
        /*Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getAppRoles().forEach(role -> {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        });*/

        //Mehode 2 avec API Stream
        Collection<GrantedAuthority> authorities = appUser
                .getAppRoles()
                .stream()
                .map(
                    role->new SimpleGrantedAuthority(role.getRoleName())
                ).collect(Collectors.toList());
        User springSecurityUser = new User(appUser.getUsername(), appUser.getPassword(), authorities);

        return springSecurityUser;
    }
}
