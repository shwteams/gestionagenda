package com.sils.gestionagenda.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {
    @Id
    private String userId;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean active;
    @ManyToMany(fetch = FetchType.EAGER) //permet de charger automatiquement en mémoire les roles
    private List<AppRole> appRoles = new ArrayList<>();
}
