package it.epicode.beservice.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.model.StatoFattura;

public interface FatturaRepository extends JpaRepository <Fattura,Long> {
	Page<Optional<Fattura>> findByCliente(Pageable page, Cliente cliente);
	
	Page<Optional<Fattura>> findByStato(Pageable page, StatoFattura stato);

	Page<Optional<Fattura>> findByData(Pageable page, LocalDate data);
	
	Page<Optional<Fattura>> findByAnno(Pageable page, String anno);
	@Query("SELECT f FROM Fattura f WHERE f.importo>=:minimo AND f.importo<=:massimo")
	Page<Optional<Fattura>> findByRangeImporto(Pageable page, Double minimo, Double massimo);
	@Query("SELECT f FROM Fattura f WHERE f.id=:id")
	Fattura getByIdFattura(Long id);



}
