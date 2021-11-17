package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;
	
	public void addProvincia (Provincia provincia) {
		provinciaRepository.save(provincia);
	}
	public void deleteProvincia (Long id) {
		provinciaRepository.deleteById(id);
	}
	public void updateProvincia (Provincia provincia) {
		Provincia prov= provinciaRepository.findByIdProvincia(provincia.getId());
		prov.setId(provincia.getId());
		prov.setNome(provincia.getNome());
		prov.setSigla(provincia.getSigla());
		provinciaRepository.flush();
	}
	
	public Provincia findByNome (String nome) {
		return provinciaRepository.findByNome(nome);
	}
}
