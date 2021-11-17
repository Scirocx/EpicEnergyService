package it.epicode.beservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.service.ProvinciaService;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {
	@Autowired
	ProvinciaService provinciaService;

	@PostMapping(value= "/addprovincia", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addProvincia(@RequestBody Provincia provincia) {
		provinciaService.addProvincia(provincia);
		return "provincia salvata";
	}

	@PostMapping(value= "/updateprovincia", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateProvincia(@RequestBody Provincia provincia) {
		provinciaService.updateProvincia(provincia);
		return "provincia aggiornata";
	}

	@GetMapping("/deleteprovincia")
	public String deleteProvincia(@RequestParam Long id) {
		provinciaService.deleteProvincia(id);
		return "provincia cancellata";
	}
}
