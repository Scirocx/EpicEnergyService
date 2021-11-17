package it.epicode.beservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public void addCliente (Cliente cliente) {
		clienteRepository.save(cliente);
	}
	public void deleteCliente(Long id) {
		clienteRepository.deleteById(id);
	}
	public void updateCliente(Cliente cliente) {
		Cliente cli= clienteRepository.findByIdCliente(cliente.getId());
		cli.setCognomeContatto(cliente.getCognomeContatto());
		cli.setDataInserimento(cliente.getDataInserimento());
		cli.setDataUltimoContatto(cliente.getDataUltimoContatto());
		cli.setEmail(cliente.getEmail());
		cli.setEmailContatto(cliente.getEmailContatto());
		cli.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
		cli.setId(cliente.getId());
		cli.setIndirizzoSedeLegale(cliente.getIndirizzoSedeLegale());
		cli.setIndirizzoSedeOperativa(cliente.getIndirizzoSedeOperativa());
		cli.setNomeContatto(cliente.getNomeContatto());
		cli.setPartitaIva(cliente.getPartitaIva());
		cli.setPec(cliente.getPec());
		cli.setRagioneSociale(cliente.getRagioneSociale());
		cli.setTelefono(cliente.getTelefono());
		cli.setTelefonoContatto(cliente.getTelefonoContatto());
		cli.setTipoCliente(cliente.getTipoCliente());
		clienteRepository.flush();
	}

	public Page <Optional<Cliente>>findByOrderByRagioneSociale (Pageable page){
		return clienteRepository.findByOrderByRagioneSociale(page);
	}
	public Page<Optional<Cliente>> findByOrderByDataInserimento (Pageable page){
		return clienteRepository.findByOrderByDataInserimento(page);
	}
	public Page <Optional<Cliente>> findByOrderByFatturatoAnnuale(Pageable page){
		return clienteRepository.findByOrderByFatturatoAnnuale(page);
	}
	public Page<Optional<Cliente>>findByOrderByDataUltimoContatto(Pageable page){
		return clienteRepository.findByOrderByDataUltimoContatto(page);
	}
	public Page<Optional<Cliente>>findByOrderByProvincia(Pageable page){
		return clienteRepository.findByOrderByIndirizzoSedeLegaleComuneProvincia(page);
	}
	public Page<Optional<Cliente>>findByFatturatoAnnuale(Pageable page,Double fatturato){
		return clienteRepository.findByFatturatoAnnuale(page, fatturato);
	}
	public Page<Optional<Cliente>>findByDataInserimento(Pageable page,LocalDate data){
		return clienteRepository.findByDataInserimento(page, data);
	}
	public Page<Optional<Cliente>>findByRagioneSociale(Pageable page,String nome){
		return clienteRepository.findByRagioneSociale(page, nome);
	}
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	public Cliente findById (Long id) {
		return clienteRepository.findByIdCliente(id);
	}
	
}
