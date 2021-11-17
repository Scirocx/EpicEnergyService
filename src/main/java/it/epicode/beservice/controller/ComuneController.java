package it.epicode.beservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.service.ComuneService;

@RestController
@RequestMapping("/comune")
public class ComuneController {
	@Autowired
	ComuneService comuneService;
	
	@PostMapping("/addcomune")
	public String addComune(@RequestBody Comune comune) {
		comuneService.addComune(comune);
		return "comune salvato";
	}

	@PostMapping("/updatecomune")
	public String updateComune(@RequestBody Comune comune) {
		comuneService.updateComune(comune);
		return "Comune aggiornato";
	}

	@GetMapping("/deletecomune")
	public String deleteComune(@RequestParam Long id) {
		comuneService.deleteComune(id);
		return "comune cancellato";
	}
}
