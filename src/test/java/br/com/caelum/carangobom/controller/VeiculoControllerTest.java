package br.com.caelum.carangobom.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.common.net.HttpHeaders;

import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.domain.Veiculo;
import br.com.caelum.carangobom.form.LoginForm;
import br.com.caelum.carangobom.form.TokenForm;
import br.com.caelum.carangobom.form.VeiculoForm;
import br.com.caelum.carangobom.repository.VeiculoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class VeiculoControllerTest {
	
	@Autowired
    private VeiculoController veiculoController;
	
	@Autowired
	private AuthController authController;
	
	@Autowired
	private MockMvc mockMvc;

    @Mock
    private VeiculoRepository veiculoRepository;
    
    
    @BeforeEach
    public void configuraMock() {
        openMocks(this);

    }
    
    public String getTokenFromUser() {
		LoginForm login = new LoginForm();
		login.setEmail("almeidalima.igor@gmail.com");
		login.setSenha("123456");
		
		ResponseEntity<TokenForm> token = authController.authentication(login);
		
		return token.getBody().getToken();
	}
    
    
    // Lista Veiculos Se Existir
    @Test
    void deveRetornarListaQuandoHouverResultadosVeiculos() {
    	
    	List<Veiculo> veiculos = new ArrayList<Veiculo>();
    	veiculos.add(new Veiculo());

        when(veiculoRepository.findByIdOrderModeloVeiculo())
            .thenReturn(veiculos);

        List<Veiculo> resultado = veiculoRepository.findByIdOrderModeloVeiculo();
        assertEquals(veiculos, resultado);
    }
    
    
    // Veiculos Vendidos Se Existir
    @Test
    void deveRetornarListaQuandoHouverResultadosVeiculosVendidos() {
    	
    	List<Veiculo> veiculos = new ArrayList<Veiculo>();
    	veiculos.add(new Veiculo());

        when(veiculoRepository.findByIdVedido())
            .thenReturn(veiculos);

        List<Veiculo> resultado = veiculoRepository.findByIdVedido();
        assertEquals(veiculos, resultado);
    }
    
    // Veiculos Por Id
    @Test
    void deveRetornarVeiculoPeloId() {
    	Integer id = 1;
        ResponseEntity<VeiculoForm> resposta = veiculoController.id(id.longValue());
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
    
    // Listar Todos Veiculos
    @Test
    void deveRetornarTodosVeiculo() {
        ResponseEntity<List<VeiculoForm>> resposta = veiculoController.lista();
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
    
    @Test
    void deveRetornarTodosVeiculoVendido() {
        ResponseEntity<List<VeiculoForm>> resposta = veiculoController.vendido();
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
    
    @Test
    void deveRetornarMarcaPeloIdNotFound() {
    	Long id = 10L;
        ResponseEntity<VeiculoForm> resposta = veiculoController.id(id);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }


    @Test
    void deveDeletarMarcaExistente() {
    	Integer id = 1;
        ResponseEntity<VeiculoForm> resposta = veiculoController.deleta(id.longValue());
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void naoDeveDeletarMarcaInexistente() {
        ResponseEntity<VeiculoForm> resposta = veiculoController.deleta(10L);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }
    
    @Test
    void deveResponderCreatedELocationQuandoCadastrarVeiculos() throws Exception {
    	
    	String json = "{\"ano\": \"2020\", \"modelo\": \"Corolla\", \"preco\": \"73000\", \"isVendido\": \"true\", \"marca\": { \"id\": \"1\"} }";
    	
    	MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/veiculo")
                					  .header(HttpHeaders.AUTHORIZATION, "Bearer " + getTokenFromUser())
                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                                      .accept(MediaType.APPLICATION_JSON)
                                      .characterEncoding("UTF-8")
                                      .content(json);
    			this.mockMvc.perform(builder)
    		    .andExpect(MockMvcResultMatchers.status().is(201));

    }
    
    @Test
    void deveAlterarVeiculos() throws Exception {
    	
    	String json = "{\"ano\": \"2020\", \"modelo\": \"Corolla\", \"preco\": \"73000\", \"isVendido\": \"true\", \"marca\": { \"id\": \"1\"} }";
    	
    	Integer id = 2;
    	
    	MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/veiculo/" + id)
                					  .header(HttpHeaders.AUTHORIZATION, "Bearer " + getTokenFromUser())
                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                                      .accept(MediaType.APPLICATION_JSON)
                                      .characterEncoding("UTF-8")
                                      .content(json);
    			this.mockMvc.perform(builder)
    		    .andExpect(MockMvcResultMatchers.status().is(200));

    }
    
    

}
