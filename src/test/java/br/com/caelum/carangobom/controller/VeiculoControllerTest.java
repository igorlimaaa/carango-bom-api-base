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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.caelum.carangobom.domain.Veiculo;
import br.com.caelum.carangobom.form.VeiculoForm;
import br.com.caelum.carangobom.repository.VeiculoRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class VeiculoControllerTest {
	
	@Autowired
    private VeiculoController veiculoController;

    @Mock
    private VeiculoRepository veiculoRepository;
    
    
    @BeforeEach
    public void configuraMock() {
        openMocks(this);

    }

//    private VeiculoForm createVeiculoForm(Long ano ,String modelo, Double preco, boolean isVendido) {
//        VeiculoForm veiculoNovo = new VeiculoForm();
//        veiculoNovo.getAno().intValue();
//        veiculoNovo.setModelo(modelo);
//        veiculoNovo.getPreco().intValue();
//        veiculoNovo.setIsVendido(isVendido);
//        return veiculoNovo;
//    }
    
    
    @Test
    void deveRetornarListaQuandoHouverResultadosVeiculos() {
    	
    	List<Veiculo> veiculos = new ArrayList<Veiculo>();
    	veiculos.add(new Veiculo());

        when(veiculoRepository.findByIdOrderModeloVeiculo())
            .thenReturn(veiculos);

        List<Veiculo> resultado = veiculoRepository.findByIdOrderModeloVeiculo();
        assertEquals(veiculos, resultado);
    }
    

    
//    @Test
//    void deveResponderCreatedVeiculo() {
//    	VeiculoForm veiculoNova = createVeiculoForm(2020, "Corolla", 77.000, true, 1L);
//        ResponseEntity<VeiculoForm> resposta = veiculoController.criarVeiculo(veiculoNova);
//        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
//    }
    
    @Test
    void deveRetornarVeiculoPeloId() {
    	Integer id = 1;
        ResponseEntity<VeiculoForm> resposta = veiculoController.id(id.longValue());
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
    
    @Test
    void deveRetornarTodosVeiculo() {
        ResponseEntity<List<VeiculoForm>> resposta = veiculoController.lista();
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
    
    @Test
    void deveRetornarMarcaPeloIdNotFound() {
    	Long id = 10L;
        ResponseEntity<VeiculoForm> resposta = veiculoController.id(id);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }
    
//    @Test
//    void deveAlterarNomeQuandoMarcaExistir() {
//    	MarcaForm nova = createVeiculoForm("NOVA Audi");
//        ResponseEntity<MarcaForm> resposta = marcaController.altera(1L, nova);
//        assertEquals(HttpStatus.OK, resposta.getStatusCode());
//        MarcaForm marcaAlterada = resposta.getBody();
//        assertEquals("NOVA Audi", marcaAlterada.getNome());
//    }

//    @Test
//    void naoDeveAlterarMarcaInexistente() {
//    	MarcaForm nova = createVeiculoForm("NOVA Toyota");
//        ResponseEntity<MarcaForm> resposta = marcaController.altera(10L, nova);
//        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
//    }

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
    
    
    
}
