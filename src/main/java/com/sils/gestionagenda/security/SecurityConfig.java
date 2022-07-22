package com.sils.gestionagenda.security;

import com.sils.gestionagenda.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//pour dire que ça sera extensier en premier
@Configuration
@EnableWebSecurity //pour dire qu'on veut appliquer la securité
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   /* @Autowired
    private DataSource dataSource;*/ //permet de faire l'injection de dépences de la base de données configuré dans le fichier application.yml
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    //Cette méthode permet de dire comment spring va rechercher les users et les roles (BDD, IN MEMORY ou ANNUAIRE)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /* //In MEMERY
        String encodedPassword = passwordEncoder.encode("1234");
        System.out.println(encodedPassword);
        auth.inMemoryAuthentication() //les user sont stocké en mémoire
                .withUser("user1")
                .password(encodedPassword) //{noop} permet de dire a spring security de ne pas encoder le mot de passe avant la vérification
                .roles("USER");
        auth.inMemoryAuthentication().withUser("user2")
                .password(encodedPassword)
                .roles("USER");
        auth.inMemoryAuthentication().withUser("admin")
                .password(encodedPassword)
                .roles("ADMIN", "USER");*/
        /*//BDD jdbc
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT u.username as principal, u.password as credentials, active FROM users u WHERE u.username = ?")
                .authoritiesByUsernameQuery("SELECT r.username as principal, r.role FROM users_roles r WHERE username = ?")
                .rolePrefix("ROLE_") //permet d'afficher un préfixe devant les roles
                .passwordEncoder(passwordEncoder);*/
        /*
        BDD par userdetailservice
         */
        auth.userDetailsService(userDetailsService); //une fois que l'utilisateur saisi son login et son mot de passe cette méthode est appelé ce qui a pour effet le lancement de la méthode loaduser

    }

    //permet de spécifier les droit d'accès
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //permet de dire a spring security qu'il faut autiliser la config par defaut
                //.loginPage("/login") //permet de dire a spring security que je veux creer mon formulaire d'authentification
                ;
        http.authorizeRequests().antMatchers("/home").permitAll(); //permet de donner accès à tout le monde a cette page
        http.authorizeRequests()
                .antMatchers("/admin/**")
                        .hasAuthority("ADMIN");
                //.hasRole("ADMIN"); //utiliser pour les autres type de gestion utilisateur (IN MEMORY et JDBC)
        http.authorizeRequests()
                .antMatchers("/user/**")
                .hasAuthority("USER");
                //.hasRole("USER"); //utiliser pour les autres type de gestion utilisateur (IN MEMORY et JDBC)
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated(); //permet de dire que toutes les requêtes HTTP necessite une authentification

        //permet d'afficher les execptions en cas d'erreurs
        http.exceptionHandling().accessDeniedPage("/403");

    }


}
