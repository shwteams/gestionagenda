package com.sils.gestionagenda;

import com.sils.gestionagenda.entities.TypeAgenda;
import com.sils.gestionagenda.repositories.TypeAgendaRepository;
import com.sils.gestionagenda.security.repositories.AppUserRepository;
import com.sils.gestionagenda.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionagendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionagendaApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	//@Bean //pour executer la méthode au démarrage de l'application
	CommandLineRunner commandLineRunner(TypeAgendaRepository typeAgendaRepository){
		return args -> {
			typeAgendaRepository.save(new TypeAgenda(null, "Visite familiale"));
			typeAgendaRepository.save(new TypeAgenda(null, "Rendez-vous professionnel"));
			typeAgendaRepository.save(new TypeAgenda(null, "Mission"));

			typeAgendaRepository.findAll().forEach(
					p->{
						System.out.println(p.getLibelle());
					}
			);
		};
	}
	//@Bean //pour executer la méthode au démarrage de l'application
	CommandLineRunner SaveUser(SecurityService securityService){
		return args -> {
			securityService.saveNewUser("user1", "demo", "demo");
			securityService.saveNewUser("root", "Gabbana12*", "Gabbana12*");
			securityService.saveNewUser("user2", "Gabbana12*", "Gabbana12*");

			securityService.saveNewRole("USER", "Role utilisateur simple.");
			securityService.saveNewRole("ADMIN", "Role administrateur.");

			securityService.addRoleToUser("user1", "USER");
			securityService.addRoleToUser("user2", "USER");
			securityService.addRoleToUser("root", "USER");
			securityService.addRoleToUser("root", "ADMIN");

		};
	}

}
