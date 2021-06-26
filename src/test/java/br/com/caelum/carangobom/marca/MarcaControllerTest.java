package br.com.caelum.carangobom.marca;

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

import br.com.caelum.carangobom.controller.MarcaController;
import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.form.MarcaForm;
import br.com.caelum.carangobom.repository.MarcaRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MarcaControllerTest {
	
    @Autowired
    private MarcaController marcaController;

    @Mock
    private MarcaRepository marcaRepository;

    @BeforeEach
    public void configuraMock() {
        openMocks(this);


//        marcaController = new MarcaController();
     //   uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");
    }
    
    private MarcaForm createMarcaForm(String nome) {
        MarcaForm nova = new MarcaForm();
        nova.setDs_nome(nome);
        return nova;
    }

    @Test
    void deveRetornarListaQuandoHouverResultados() {
    	
    	List<Marca> marcas = new ArrayList<Marca>();
    	marcas.add(new Marca(1L, "Audi"));
    	marcas.add(new Marca(2L, "BMW"));
    	marcas.add(new Marca(3L, "Fiat"));

        when(marcaRepository.findByIdOrderNome())
            .thenReturn(marcas);

        List<Marca> resultado = marcaRepository.findByIdOrderNome();
        assertEquals(marcas, resultado);
    }

    @Test
    void deveRetornarMarcaPeloId() {
        ResponseEntity<Marca> resposta = marcaController.id(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveRetornarNotFoundQuandoRecuperarMarcaComIdInexistente() {
        ResponseEntity<Marca> resposta = marcaController.id(10L);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

    @Test
    void deveResponderCreatedELocationQuandoCadastrarMarca() {
        MarcaForm nova = createMarcaForm("Toyota");
        ResponseEntity<MarcaForm> resposta = marcaController.cadastra(nova);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    }

    @Test
    void deveAlterarNomeQuandoMarcaExistir() {
    	MarcaForm nova = createMarcaForm("NOVA Audi");
        ResponseEntity<MarcaForm> resposta = marcaController.altera(1L, nova);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        MarcaForm marcaAlterada = resposta.getBody();
        assertEquals("NOVA Audi", marcaAlterada.getDs_nome());
    }

    @Test
    void naoDeveAlterarMarcaInexistente() {
    	MarcaForm nova = createMarcaForm("NOVA Toyota");
        ResponseEntity<MarcaForm> resposta = marcaController.altera(10L, nova);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

    @Test
    void deveDeletarMarcaExistente() {
        ResponseEntity<Marca> resposta = marcaController.deleta(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void naoDeveDeletarMarcaInexistente() {
        ResponseEntity<Marca> resposta = marcaController.deleta(10L);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

}