package br.com.caelum.carangobom.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.caelum.carangobom.form.LoginForm;
import br.com.caelum.carangobom.form.TokenForm;

@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration(classes = CarangoBomApiApplication.class)
@ActiveProfiles("test")
class AuthControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AuthController authController;

	@Test
	void deveriaRetornar400CasoDadosDeAutenticacaoEstejamErrados() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"email\": \"invalid@gmail.com\", \"senha\": \"123456\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	void deveriaRetornar200Sucesso() throws Exception{
		URI uri = new URI("/auth");
		String json = "{\"email\": \"almeidalima.igor@gmail.com\", \"senha\": \"123456\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	public String getTokenFromUser() {
		LoginForm login = new LoginForm();
		login.setEmail("almeidalima.igor@gmail.com");
		login.setSenha("123456");
		
		ResponseEntity<TokenForm> token = authController.authentication(login);
		
		return token.getBody().getToken();
	}

}
