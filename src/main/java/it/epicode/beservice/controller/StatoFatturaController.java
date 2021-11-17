package it.epicode.beservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.service.StatoFatturaService;

@RestController
@RequestMapping("/statofattura")
public class StatoFatturaController {
	@Autowired
	StatoFatturaService statoFatturaService;
	
	@PostMapping(value= "/addstatofattura", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addStatoFattura(@RequestBody StatoFattura statoFattura) {
		statoFatturaService.addStatoFattura(statoFattura);
		return "stato fattura salvato";
	}

	@PostMapping(value= "/updatestatofattura", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateStatoFattura(@RequestBody StatoFattura statoFattura) {
		statoFatturaService.updateStatoFattura(statoFattura);
		return "stato fattura aggiornato";
	}

	@GetMapping("/deletestatofattura")
	public String deleteStatoFattura(@RequestParam Long id) {
		statoFatturaService.deleteStatoFattura(id);
		return "stato fattura cancellato";
	}
}
