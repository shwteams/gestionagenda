package com.sils.gestionagenda.repositories;

import com.sils.gestionagenda.entities.TypeAgenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TypeAgendaRepository extends JpaRepository<TypeAgenda, Long> {
    Page<TypeAgenda> findByLibelleContains(String liblle, Pageable pageable);
}
