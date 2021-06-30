package br.com.caelum.carangobom.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioControllerTest {
	
//	@Autowired
//	private UsuarioController usuarioControler;
//	
//	@Autowired
//	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveCadastrarUsuario() throws Exception {
		URI uri = new URI("/usuario");
		String json = "{\"ds_nome\": \"Igor de Almeida Lima\", \"ds_email\": \"almeidalima.igor@gmail.com\", \"ds_senha\": \"123456\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201));
		
	}

}
