package it.epicode.beservice.serviceTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.service.StatoFatturaService;

@ExtendWith(MockitoExtension.class)
class StatoFatturaServiceTest {
	
	
	@Mock StatoFatturaService statoFatturaService;
	StatoFattura stat;
	@BeforeEach
	void setUp() throws Exception {
	StatoFattura stat= new StatoFattura();
	stat.setNome("PAGATO");
	}

	@Test
	@DisplayName("test aggiungi fattura")
	void testAddStatoFattura() {
	 statoFatturaService.addStatoFattura(stat);
	}

}
