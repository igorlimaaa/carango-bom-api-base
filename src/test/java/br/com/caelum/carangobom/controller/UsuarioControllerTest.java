package br.com.caelum.carangobom.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.caelum.carangobom.form.UsuarioForm;

@SpringBootTest
@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UsuarioController usuarioController;

	@Test
	void deveCadastrarUsuario() throws Exception {
		
		UsuarioForm newUsuario = new UsuarioForm();
		newUsuario.setName("Priscilla Basto");
		newUsuario.setEmail("priii@gmail.com");
		newUsuario.setSenha("123456");
		
		ResponseEntity<UsuarioForm> cadastroUsuario = usuarioController.cadastraUsuario(newUsuario);
		boolean result = cadastroUsuario.getBody() != null && cadastroUsuario.getStatusCode() == HttpStatus.CREATED ? true : false;
		
		assertTrue(result);
		
//		URI uri = new URI("/usuario");
//		String json = "{\"name\": \"Priscilla Basto\", \"email\": \"priii@gmail.com\", \"senha\": \"123456\"}";
//		
//		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201));
//		
	}
	
	@Test
	void getListaUsuarios() {
		List<UsuarioForm> usuarioType = usuarioController.lista();
		boolean result = usuarioType != null && !usuarioType.isEmpty() ? true : false;
		assertTrue(result);
	}
	
	@Test
	void deveDeletarUsuarioComSucesso() throws Exception {
		Integer idUsuario = 1;
		ResponseEntity<UsuarioForm> usuarioDel = usuarioController.deleta(idUsuario.longValue());
		assertEquals(HttpStatus.OK, usuarioDel.getStatusCode());
		assertEquals(idUsuario, usuarioDel.getBody().getId().intValue());
		
	}
	
	@Test
	void deletarUsuarioInexistente() throws Exception {
		Integer idUsuario = 1000;
		ResponseEntity<UsuarioForm> usuarioDel = usuarioController.deleta(idUsuario.longValue());
		assertEquals(HttpStatus.NOT_FOUND, usuarioDel.getStatusCode());
		
	}

}
