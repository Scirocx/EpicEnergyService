package it.epicode.beservice.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Provincia;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Page<Optional<Cliente>> findByOrderByRagioneSociale(Pageable page);

	Page<Optional<Cliente>> findByOrderByFatturatoAnnuale(Pageable page);

	Page<Optional<Cliente>> findByOrderByDataInserimento(Pageable page);

	Page<Optional<Cliente>> findByOrderByDataUltimoContatto(Pageable page);

	Page<Optional<Cliente>> findByOrderByIndirizzoSedeLegaleComuneProvincia(Pageable page);
	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale>=:fatturato")
	Page<Optional<Cliente>> findByFatturatoAnnuale (Pageable page, Double fatturato);
	
	Page<Optional<Cliente>> findByDataInserimento (Pageable page, LocalDate data);
	
	Page<Optional<Cliente>> findByDataUltimoContatto (Pageable page, LocalDate data);
	@Query ("SELECT c FROM Cliente c WHERE c.ragioneSociale LIKE %:nome%")
	Page<Optional<Cliente>> findByRagioneSociale (Pageable page, String nome);
	@Query("SELECT c FROM Cliente c WHERE c.id=:id")
	Cliente findByIdCliente (Long id);
	
	


}
