package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.repository.StatoFatturaRepository;

@Service
public class StatoFatturaService {
	@Autowired
	StatoFatturaRepository statoFatturaRepository;
	
	
	public void addStatoFattura (StatoFattura statoFattura) {
		statoFatturaRepository.save(statoFattura);
	}
	public void deleteStatoFattura (Long id) {
		statoFatturaRepository.deleteById(id);
	}
	public void updateStatoFattura (StatoFattura statoFattura) {
		StatoFattura statoFatt= statoFatturaRepository.findByIdStatoFattura(statoFattura.getId());
		statoFatt.setId(statoFattura.getId());
		statoFatt.setNome(statoFattura.getNome());
		statoFatturaRepository.flush();
	}
}
