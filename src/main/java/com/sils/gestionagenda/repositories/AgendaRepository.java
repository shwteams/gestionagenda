package com.sils.gestionagenda.repositories;

import com.sils.gestionagenda.entities.Agenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Page<Agenda> findAgendaByTitreContains(String titre, Pageable pageable);
}
