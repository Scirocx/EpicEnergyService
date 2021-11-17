package it.epicode.beservice.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.service.FatturaService;

@RestController
@RequestMapping("/fattura")
public class FatturaController {
	@Autowired
	FatturaService fatturaService;
	
	@PostMapping(value="/addfattura", produces=MediaType.APPLICATION_JSON_VALUE)
	public String saveFattura(@RequestBody Fattura fattura) {
		this.fatturaService.addFattura(fattura);
		return "fattura salvata";
	}

	@PostMapping("/updatefattura")
	public String updateFattura(@RequestBody Fattura fattura) {
		this.fatturaService.updateFattura(fattura);
		return "fattura aggiornata";
	}

	@GetMapping("/deletefattura")
	public String deleteFattura(@RequestParam Long id) {
		this.fatturaService.deleteFattura(id);
		return "fattura cancellata";
	}
	
	@PostMapping("/findbycliente")
	public ResponseEntity<?> findByCliente (Pageable page,@RequestBody Cliente cliente){
		Page<Optional<Fattura>> findAll= fatturaService.findByCliente(page,cliente);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@PostMapping("/findbystato")
	public ResponseEntity<?> findByStato (Pageable page,@RequestBody StatoFattura stato){
		Page<Optional<Fattura>> findAll= fatturaService.findByStato(page,stato);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@GetMapping("/findbydata")
	public ResponseEntity<?> findByData (Pageable page,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data){
		Page<Optional<Fattura>> findAll= fatturaService.findByData(page,data);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@GetMapping("/findbyanno")
	public ResponseEntity<?> findByAnno (Pageable page,@RequestParam String anno){
		Page<Optional<Fattura>> findAll= fatturaService.findByAnno(page,anno);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@GetMapping("/findbyrange")
	public ResponseEntity<?> findbyImporto(Pageable page,@RequestParam String minP,@RequestParam String maxP){
		
		Double min=Double.parseDouble(minP);
		Double max=Double.parseDouble(maxP);
		Page<Optional<Fattura>> find = fatturaService.findByRangeImporto(page, min, max);
		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
