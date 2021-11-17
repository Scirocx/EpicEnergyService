package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.beservice.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
	@Query("SELECT p FROM Provincia p WHERE p.id=:id")
	Provincia findByIdProvincia(Long id);

	Provincia findByNome(String nome);
}
