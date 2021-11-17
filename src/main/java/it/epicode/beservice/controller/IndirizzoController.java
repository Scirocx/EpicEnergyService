package it.epicode.beservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.service.IndirizzoService;

@RestController
@RequestMapping("/indirizzo")
public class IndirizzoController {
	@Autowired
	IndirizzoService indirizzoService;

	@PostMapping(value="/addindirizzo", produces=MediaType.APPLICATION_JSON_VALUE)
	public String addIndirizzo(@RequestBody Indirizzo indirizzo) {
		indirizzoService.addIndirizzo(indirizzo);
		return "indirizzo salvato";
	}

	@PostMapping("/updateindirizzo")
	public String updateIndirizzo(@RequestBody Indirizzo indirizzo) {
		indirizzoService.updateIndirizzo(indirizzo);
		return "indirizzo aggiornato";
	}

	@GetMapping("/deleteindirizzo")
	public String deleteIndirizzo(@RequestParam Long id) {
		indirizzoService.deleteIndirizzo(id);
		return "indirizzo cancellato";
	}
}
