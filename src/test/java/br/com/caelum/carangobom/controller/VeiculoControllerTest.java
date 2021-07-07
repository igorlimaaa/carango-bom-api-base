package br.com.caelum.carangobom.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.caelum.carangobom.form.MarcaForm;
import br.com.caelum.carangobom.form.VeiculoForm;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class VeiculoControllerTest {
	
	@Autowired
    private VeiculoController veiculoController;  
    
    @BeforeEach
    public void configuraMock() {
        openMocks(this);

    }
    
    //private final static String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgQ2FyYW5nbyBCb20iLCJzdWIiOiIzIiwiaWF0IjoxNjI1MTY3ODUyLCJleHAiOjE2Mjc3NTk4NTJ9.T1rCYcSeK0Mcb0_QhpJ5Ts19F5VXWo-Y3Gbih15m7L8";
        
    // Lista Veiculos Se Existir
    @Test
    void deveRetornarListaQuandoHouverResultadosVeiculos() {
    	
    	ResponseEntity<List<VeiculoForm>> listVeiculo = veiculoController.lista();
    	List<VeiculoForm> body = listVeiculo.getBody();
    	boolean result = body != null && !body.isEmpty() ? true : false;
    	assertTrue(result);
    	
    }
    
    
    // Veiculos Vendidos Se Existir
    @Test
    void deveRetornarListaQuandoHouverResultadosVeiculosVendidos() {
    	
    	ResponseEntity<List<VeiculoForm>> listVeiculo = veiculoController.vendido();
    	List<VeiculoForm> body = listVeiculo.getBody();
    	boolean result = body != null && !body.isEmpty() ? true : false;
    	assertTrue(result);
    	
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
    void deveResponderCreatLocationQuandoCadastrarVeiculos() throws Exception {
    	
    	ResponseEntity<VeiculoForm> resposta = veiculoController.criarVeiculo(createVeiculoForm());
    	
    	assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }
    
    @Test
    void deveAlterarVeiculos() throws Exception {
    	
    	Integer id = 2;
    	ResponseEntity<VeiculoForm> resposta = veiculoController.updateVeiculo(id.longValue(), createVeiculoForm());
    	
    	assertEquals(HttpStatus.OK, resposta.getStatusCode());

    }
    
    @Test
    void naoDeveAlterarVeiculoInexistente() throws Exception {
    	
    	Integer id = 2000;
    	ResponseEntity<VeiculoForm> resposta = veiculoController.updateVeiculo(id.longValue(), createVeiculoForm());
    	
    	assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());

    }
    
    private VeiculoForm createVeiculoForm() {
    	VeiculoForm veiculo = new VeiculoForm();
    	veiculo.setAno(2020L);
    	veiculo.setModelo("Corolla");
    	veiculo.setPreco((double) 73000);
    	veiculo.setIsVendido(true);
    	MarcaForm marca = new MarcaForm();
    	marca.setId(2L);
    	veiculo.setMarca(marca);
    	return veiculo;
    }
    

}
