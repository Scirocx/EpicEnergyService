package it.epicode.beservice.serviceTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import it.epicode.beservice.repository.ClienteRepository;
import it.epicode.beservice.service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class testCliente {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	ClienteRepository clienteRepo;

	@Mock
	ClienteService clienteService;

	@Test
	void testRagioneSociale() throws Exception {
		this.mockMvc.perform(get("/cliente/ragionesociale")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("rag")));
	}


	@Test
	public void testAddCliente() throws Exception {
		this.mockMvc.perform(post("/cliente/addcliente").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\r\n" + "\"ragioneSociale\":\"ragionesociale18\",\r\n"
						+ "    \"partitaIva\":\"9687521\",\r\n" 
						+ "    \"tipoCliente\":\"SRL\",\r\n"
						+ "    \"email\":\"email52@gmail.com\",\r\n"
						+ "    \"pec\":\"email52@gmail.com\",\r\n"
						+ "    \"telefono\":\"123456\",\r\n" 
						+ "    \"nomeContatto\":\"nomecontatto2\",\r\n"
						+ "    \"cognomeContatto\":\"cognomecontatto2\",\r\n"
						+ "    \"telefonoContatto\":\"1234567\",\r\n"
						+ "    \"emailContatto\":\"emailcontatto@email.com\",\r\n"
						+ "    \"indirizzoSedeOperativa\":{\r\n" 
						+ "    \"id\":\"2\",    \r\n"
						+ "    \"via\":\"Via 3\",\r\n" 
						+ "    \"civico\":\"123\",\r\n" + "    \"cap\":\"00000\",\r\n"
						+ "    \"localita\":\"localita3\",\r\n" + "    \"comune\":{\r\n" + "        \"id\":\"545\",\r\n"
						+ "        \"nome\":\"Ceva\",\r\n" + "        \"provincia\":{\r\n"
						+ "            \"id\":\"32\",\r\n" + "            \"nome\":\"Ceva\",\r\n"
						+ "            \"sigla\":\"CN\"\r\n" + "\r\n" + "        }\r\n" + "    }\r\n" + "},\r\n"
						+ "\"indirizzoSedeLegale\":{\r\n" + "    \"id\":\"2\",    \r\n" + "    \"via\":\"Via 3\",\r\n"
						+ "    \"civico\":\"123\",\r\n" + "    \"cap\":\"00000\",\r\n"
						+ "    \"localita\":\"localita3\",\r\n" + "    \"comune\":{\r\n" + "        \"id\":\"545\",\r\n"
						+ "        \"nome\":\"Ceva\",\r\n" + "        \"provincia\":{\r\n"
						+ "            \"id\":\"32\",\r\n" + "            \"nome\":\"Ceva\",\r\n"
						+ "            \"sigla\":\"CN\"\r\n" + "\r\n" + "        }\r\n" + "    }\r\n" + "},\r\n"
						+ "\"dataInserimento\":\"2021-12-12\",\r\n" + "\"dataUltimoContatto\":\"2021-12-12\",\r\n"
						+ "\"fatturatoAnnuale\":\"10000\"\r\n" + "}")
				.with(csrf())).andExpect(content().string("cliente salvato"));
		
	}
}