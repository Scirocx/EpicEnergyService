package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.beservice.model.StatoFattura;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
	@Query("SELECT s FROM StatoFattura s WHERE s.id=:id")
	StatoFattura findByIdStatoFattura(Long id);
}
