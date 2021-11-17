package it.epicode.beservice.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Erole;
import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.User;
import it.epicode.beservice.repository.RoleRepository;
import it.epicode.beservice.repository.UserRepository;
import it.epicode.beservice.security.JwtUtils;
import it.epicode.beservice.security.UserDetailsImpl;
import it.epicode.beservice.security.request.LoginRequest;
import it.epicode.beservice.security.request.SignupRequest;
import it.epicode.beservice.service.ClienteService;
import it.epicode.beservice.service.IndirizzoService;

@Controller
@RequestMapping("/apihtml")
public class HtmlController {
	  @Autowired
	    AuthenticationManager authenticationManager;
	    @Autowired
	    UserRepository userRepository;
	    @Autowired
	    JwtUtils jwtUtils;
	    @Autowired
	    RoleRepository roleRepository;
	    @Autowired
	    PasswordEncoder encoder;
	    @Autowired
		ClienteService clienteService;
	    @Autowired
	    IndirizzoService indirizzoService;
	    
	    @PostMapping(value="/login2", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	    public ModelAndView authenticateUser1(LoginRequest loginRequest) {
	        // Usa l'AuthenticationManager per autenticare i parametri della request
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	        // Ottiene i privilegi dell'utente
	        authentication.getAuthorities();
	        
	        // Ottiene il SecurityContext
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        // Genera il token
	        String jwt = jwtUtils.generateJwtToken(authentication);
	        
	        // getPrincipal(), ottiene i dati dell'utente
	        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
	        ModelAndView view= new ModelAndView ("menu.html");
	        view.addObject("jwt", jwt);
	        view.addObject("roles",roles);
	        // Restituisce la view
	        return view;
	    }
	    
	    @PostMapping(value="/signup2", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	    public ModelAndView registerUser2(SignupRequest signUpRequest) {
	        // Verifica l'esistenza di Username e Email già registrate
	        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	        	return new ModelAndView("error.html");
	        }
	        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	        	return new ModelAndView("error.html");
	        }
	        // Crea un nuovo user codificando la password
	        User user = new User(null, signUpRequest.getUsername(), signUpRequest.getEmail(),    encoder.encode(signUpRequest.getPassword()), signUpRequest.getNome(), signUpRequest.getCognome());
	        Set<String> strRoles = signUpRequest.getRole();
	        Set<Role> roles = new HashSet<>();
	     // Verifica l'esistenza dei Role
	        if (strRoles == null) {
	            Role userRole = roleRepository.findByEroles(Erole.ROLE_USER).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
	            roles.add(userRole);
	        } else {
	            strRoles.forEach(role -> {
	                switch (role.toLowerCase()) {
	                case "admin":
	                    Role adminRole = roleRepository.findByEroles(Erole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
	                    roles.add(adminRole);
	                    break;
	                default:
	                    Role userRole = roleRepository.findByEroles(Erole.ROLE_USER).orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
	                    roles.add(userRole);
	                }
	            });
	        }
	        user.setRoles(roles);
	        userRepository.save(user);
	        return new ModelAndView ("homepage.html");
	    }

	    @PostMapping (value= "/addcliente",produces = MediaType.APPLICATION_JSON_VALUE)
		public String addCliente (Cliente cliente) {
			clienteService.addCliente(cliente);
			return "cliente salvato";
		}
		
		@PostMapping(value="/updatecliente",produces = MediaType.APPLICATION_JSON_VALUE)
		public String updateCliente ( Cliente cliente) {
			clienteService.updateCliente(cliente);
			return "aggiornamento effettuato";
		}
		
		@GetMapping("showindirizzo/{indirizzo}")
		public ModelAndView showIndirizzo (@PathVariable Long indirizzo, ModelAndView model) {
			Indirizzo i=indirizzoService.findById(indirizzo);
			model.setViewName("showindirizzo");
			model.addObject("indirizzo",i);
			return model;

//			if(c.getIndirizzoSedeLegale().equals(indirizzo)) {
//				model.setViewName("showindirizzo");
//				model.addObject("indirizzo",c.getIndirizzoSedeLegale());
//				return model;
//			}else {
//				model.setViewName("showindirizzo");
//				model.addObject("indirizzo",c.getIndirizzoSedeOperativa());
//				return model;
//			}
				
		}
		
		@GetMapping("/updateindirizzo/{indirizzo})")
		public String updateIndirizzo (@PathVariable Long indirizzo) { 
			Indirizzo i= indirizzoService.findById(indirizzo);
			indirizzoService.updateIndirizzo(i);
			return "/apihtml/findall";
		}
		
		@GetMapping("/deletecliente/{id}")
		public ModelAndView deleteCliente(@PathVariable Long id) {
			clienteService.deleteCliente(id);
			return findAll();
		}
		
		@GetMapping("/findall")
	 	public ModelAndView findAll(){
	 	List<Cliente> find=this.clienteService.findAll();
	 		if(!(find.isEmpty())) {
	 			ModelAndView view=new ModelAndView("list.html");
	 			view.addObject("find",find);
	 			return  view;
	 		}else {
	 			ModelAndView badrequest=new ModelAndView("badrequest.html");
	 			return  badrequest;}
	 	}
		
		@GetMapping("/orderbyragionesociale/{pageNO}")
			public ModelAndView findByOrderByRagioneSociale (Pageable pageable,
					@RequestParam(required = false, defaultValue = "0") int pageNo) {
				Page<Optional<Cliente>> find= clienteService.findByOrderByRagioneSociale(pageable);
				List <Optional<Cliente>> list= find.getContent();
				if (find.hasContent()) {
				ModelAndView view= new ModelAndView ("listaordinata.html");
				view.addObject("list",list);
				view.addObject("currentPage", pageNo);
				view.addObject("totalPages", find.getTotalPages());
				return view;
				}else {
					ModelAndView error= new ModelAndView("error.html");
					return error;	
				}
		}
		
		@GetMapping("/orderbydatainserimento/{pageNo}")
			public ModelAndView findByOrderByDataInserimento (Pageable pageable,					
				@RequestParam(required = false, defaultValue = "0") int pageNo) {
				Page<Optional<Cliente>> find= clienteService.findByOrderByDataInserimento(pageable);
				List <Optional<Cliente>> list= find.getContent();
				if (find.hasContent()) {
				ModelAndView view= new ModelAndView ("listaordinata.html");
				view.addObject("list",list);
				view.addObject("currentPage", pageNo);
				view.addObject("totalPages", find.getTotalPages());
				return view;
				}else {
					ModelAndView error= new ModelAndView("error.html");
					return error;	
				}
		}
	
		@GetMapping("/orderbyfatturatoannuale/{pageNo}")
		public ModelAndView findByOrderByFatturatoAnnuale (Pageable pageable,
				@RequestParam(required = false, defaultValue = "0") int pageNo) {
			Page<Optional<Cliente>> find= clienteService.findByOrderByFatturatoAnnuale(pageable);
			List <Optional<Cliente>> list= find.getContent();
			if (find.hasContent()) {
			ModelAndView view= new ModelAndView ("listaordinata.html");
			view.addObject("list",list);
			view.addObject("currentPage", pageNo);
			view.addObject("totalPages", find.getTotalPages());
			return view;
			}else {
				ModelAndView error= new ModelAndView("error.html");
				return error;	
			}
	}
	
		@GetMapping("/orderbydatautlimocontatto/{pageNo}")
		public ModelAndView findByOrderByDataUltimoContatto (Pageable pageable,				
				@RequestParam(required = false, defaultValue = "0") int pageNo) {
			Page<Optional<Cliente>> find= clienteService.findByOrderByDataUltimoContatto(pageable);
			List <Optional<Cliente>> list= find.getContent();
			if (find.hasContent()) {
			ModelAndView view= new ModelAndView ("listaordinata.html");
			view.addObject("list",list);
			view.addObject("currentPage", pageNo);
			view.addObject("totalPages", find.getTotalPages());
			return view;
			}else {
				ModelAndView error= new ModelAndView("error.html");
				return error;	
			}
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
