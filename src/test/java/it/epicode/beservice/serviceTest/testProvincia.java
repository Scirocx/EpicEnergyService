package it.epicode.beservice.serviceTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class testProvincia {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testAddProvincia() throws Exception {
		this.mockMvc.perform(post("/provincia/addprovincia").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\r\n" + "\"nome\":\"NAPOLI\",\r\n" +"}").with(csrf())).andExpect(content().string("provincia salvata"));
	}

}
