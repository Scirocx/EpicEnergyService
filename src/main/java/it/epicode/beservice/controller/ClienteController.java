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
import it.epicode.beservice.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@PostMapping (value= "/addcliente",produces = MediaType.APPLICATION_JSON_VALUE)
	public String addCliente (@RequestBody Cliente cliente) {
		clienteService.addCliente(cliente);
		return "cliente salvato";
	}
	
	@PostMapping(value="/updatecliente",produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateCliente (@RequestBody Cliente cliente) {
		clienteService.updateCliente(cliente);
		return "aggiornamento effettuato";
	}
	
	@GetMapping("/deletecliente")
	public String deleteCliente(@RequestParam Long id) {
		clienteService.deleteCliente(id);
		return "Cliente eliminato";
	}
	
	@GetMapping("/orderbyragionesociale")
		public ResponseEntity<?> findByOrderByRagioneSociale (Pageable pageable){
			Page<Optional<Cliente>> findAll= clienteService.findByOrderByRagioneSociale(pageable);
			if (findAll.hasContent()) {
	            return new ResponseEntity<> (findAll, HttpStatus.OK);
			}else 
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
	}
	

	@GetMapping("/orderbydatainserimento")
		public ResponseEntity<?> findByOrderByDataInserimento (Pageable pageable){
			Page<Optional<Cliente>> findAll= clienteService.findByOrderByDataInserimento(pageable);
			if (findAll.hasContent()) {
				return new ResponseEntity<> (findAll, HttpStatus.OK);
			}else 
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@GetMapping("/orderbyfatturatoannuale")
	public ResponseEntity<?> findByOrderByFatturatoAnnuale (Pageable pageable){
		Page<Optional<Cliente>> findAll= clienteService.findByOrderByFatturatoAnnuale(pageable);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@GetMapping("/orderbydatautlimocontatto")
	public ResponseEntity<?> findByOrderByDataUltimoContatto (Pageable pageable){
		Page<Optional<Cliente>> findAll= clienteService.findByOrderByDataUltimoContatto(pageable);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@GetMapping ("/orderbyprovincia")
	public ResponseEntity<?> findByOrderByProvincia (Pageable page){
		Page<Optional<Cliente>> findAll= clienteService.findByOrderByProvincia(page);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@GetMapping("/fatturatoannuale")
	public ResponseEntity<?> findByFatturatoAnnuale (Pageable pageable,@RequestParam Double fatturato){
		 
		Page<Optional<Cliente>> findAll= clienteService.findByFatturatoAnnuale(pageable, fatturato);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@GetMapping("/datainserimento")
	public ResponseEntity<?> findByDataInserimento (Pageable pageable, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data){
		Page<Optional<Cliente>> findAll= clienteService.findByDataInserimento(pageable,data);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	@GetMapping("/ragionesociale")
	public ResponseEntity<?> findByRagioneSociale (Pageable pageable, String nome){
		Page<Optional<Cliente>> findAll= clienteService.findByRagioneSociale(pageable,nome);
		if (findAll.hasContent()) {
            return new ResponseEntity<> (findAll, HttpStatus.OK);
		}else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);			
}
	
}	
	
	
	
