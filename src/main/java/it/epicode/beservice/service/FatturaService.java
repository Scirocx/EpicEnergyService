package it.epicode.beservice.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.repository.FatturaRepository;

@Service
public class FatturaService {
	
	@Autowired
	FatturaRepository fatturaRepository;
	
	public void addFattura(Fattura fattura) {
		fatturaRepository.save(fattura);
	}
	public void deleteFattura(Long id) {
		fatturaRepository.deleteById(id);
	}
	public void updateFattura(Fattura fattura) {
		Fattura fat= fatturaRepository.getByIdFattura(fattura.getId());
		fat.setAnno(fattura.getAnno());
		fat.setCliente(fattura.getCliente());
		fat.setData(fattura.getData());
		fat.setId(fattura.getId());
		fat.setImporto(fattura.getImporto());
		fat.setNumero(fattura.getNumero());
		fat.setStato(fattura.getStato());
		fatturaRepository.flush();
	}
	
	public Page <Optional<Fattura>> findByCliente (Pageable page,Cliente cliente){
		return fatturaRepository.findByCliente(page, cliente);
	}
	public Page<Optional<Fattura>> findByStato (Pageable page,StatoFattura stato){
		return fatturaRepository.findByStato(page, stato);
	}
	public Page<Optional<Fattura>> findByData (Pageable page, LocalDate data){
		return fatturaRepository.findByData(page, data);
	}
	public Page<Optional<Fattura>> findByAnno (Pageable page, String anno){
		return fatturaRepository.findByAnno(page, anno);
	}
	 public Page<Optional<Fattura>> findByRangeImporto(Pageable page, Double minimo, Double massimo){
		 return fatturaRepository.findByRangeImporto(page, minimo, massimo);
	 }


}
