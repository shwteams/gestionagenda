package com.sils.gestionagenda.repositories;

import com.sils.gestionagenda.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
