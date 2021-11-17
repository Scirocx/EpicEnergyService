package it.epicode.beservice.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.model.Erole;
import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.service.ComuneService;
import it.epicode.beservice.model.Role;
import it.epicode.beservice.repository.RoleRepository;
import it.epicode.beservice.service.ProvinciaService;

//@Component
public class Commons implements CommandLineRunner {

	@Autowired
	ComuneService comuneService;
	
	@Autowired
	ProvinciaService provinciaService;
	
	@Autowired
	RoleRepository roleRepo;
	
	private static final String PATH_PROVINCIA="C:\\Users\\ricca\\Downloads\\province-italiane (1).csv";
	private static final String PATH_COMUNE="C:\\Users\\ricca\\Downloads\\comuni-italiani (1).csv";
	
	@Override
	public void run(String... args) throws Exception {
		
		Role role=new Role();
			role.setEroles(Erole.ROLE_ADMIN);
			this.roleRepo.save(role);
			Role roleUser=new Role();
			roleUser.setEroles(Erole.ROLE_USER);
			this.roleRepo.save(roleUser);
		recuperoDaCsvProvincia();
		recuperoDaCsvComune();
		
	}

	private void recuperoDaCsvComune() {
		try 
			(BufferedReader br= new BufferedReader(new FileReader(PATH_COMUNE))){
			String line;
			while ((line=br.readLine()) != null) {
				String[]part=line.split(";");
				Comune comune= new Comune();
				comune.setNome(part[2]);
				Provincia prov =provinciaService.findByNome(part[3]);
				comune.setProvincia(prov);
				comuneService.addComune(comune);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void recuperoDaCsvProvincia() {
		try 
			(BufferedReader br= new BufferedReader(new FileReader(PATH_PROVINCIA))){
			String line;
			while((line=br.readLine()) != null) {
				String[]part=line.split(";");
				Provincia prov= new Provincia();
				prov.setSigla(part[0]);
				prov.setNome(part[1]);
				provinciaService.addProvincia(prov);
			}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}

	
	
	
	
	

