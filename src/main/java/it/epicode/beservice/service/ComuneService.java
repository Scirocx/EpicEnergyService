package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
	
	public void addComune(Comune comune) {
		comuneRepository.save(comune);
	}
	public void deleteComune (Long id) {
		comuneRepository.deleteById(id);
	}
	public void updateComune (Comune comune) {
		Comune com= comuneRepository.findByIdComune(comune.getId());
		com.setId(comune.getId());
		com.setNome(comune.getNome());
		com.setProvincia(comune.getProvincia());
		comuneRepository.flush();
	}
}
