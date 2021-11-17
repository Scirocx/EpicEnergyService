package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.beservice.model.Comune;

public interface ComuneRepository extends JpaRepository<Comune, Long> {
	@Query("SELECT c FROM Comune c WHERE c.id=:id")
	Comune findByIdComune(Long id);

}
