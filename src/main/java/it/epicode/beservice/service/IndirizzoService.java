package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;
	
	public void addIndirizzo (Indirizzo indirizzo) {
		indirizzoRepository.save(indirizzo);
	}
	public void deleteIndirizzo (Long id) {
		indirizzoRepository.deleteById(id);
	}
	public void updateIndirizzo (Indirizzo indirizzo) {
		Indirizzo ind=indirizzoRepository.findByIdIndirizzo(indirizzo.getId());
		ind.setCap(indirizzo.getCap());
		ind.setCivico(indirizzo.getCivico());
		ind.setComune(indirizzo.getComune());
		ind.setId(indirizzo.getId());
		ind.setLocalita(indirizzo.getLocalita());
		ind.setVia(indirizzo.getVia());
		indirizzoRepository.flush();
	}
	public Indirizzo findById(Long idI) {
		return indirizzoRepository.findByIdIndirizzo(idI);
	}
}
