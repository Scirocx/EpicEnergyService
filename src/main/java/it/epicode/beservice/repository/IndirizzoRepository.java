package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.beservice.model.Indirizzo;

public interface IndirizzoRepository extends JpaRepository <Indirizzo,Long> {
	@Query("SELECT i FROM Indirizzo i WHERE i.id=:id")
	Indirizzo findByIdIndirizzo(Long id);

}
