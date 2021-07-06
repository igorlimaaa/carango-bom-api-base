package br.com.caelum.carangobom.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.exception.MarcaAssociadaException;
import br.com.caelum.carangobom.form.DashboardForm;
import br.com.caelum.carangobom.form.MarcaForm;
import br.com.caelum.carangobom.repository.MarcaRepository;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MarcaControllerTest {

	@Autowired
	private MarcaController marcaController;

	@Mock
	private MarcaRepository marcaRepository;

	@Mock
	BindingResult bindingResult;

	@BeforeEach
	public void configuraMock() {
		openMocks(this);
	}

	private MarcaForm createMarcaForm(String nome) {
		MarcaForm nova = new MarcaForm();
		nova.setNome(nome);
		return nova;
	}

	@Test
	void deveRetornarListaQuandoHouverResultados() {
		ResponseEntity<List<MarcaForm>> listMarcas = marcaController.lista();
		boolean result = listMarcas.getBody() != null && !listMarcas.getBody().isEmpty() ? true : false;
		assertTrue(result);
	}

	@Test
	void deveRetornarMarcaPeloId() {
		ResponseEntity<Marca> resposta = marcaController.id(1L);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	void deveRetornarNotFoundQuandoRecuperarMarcaComIdInexistente() {
		ResponseEntity<Marca> resposta = marcaController.id(1000L);
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
		assertEquals("NOVA Audi", marcaAlterada.getNome());
	}

	@Test
	void deveAlterarNomeQuandoMarcaExistirComNomeNull() {
		MarcaForm nova = createMarcaForm("NOVA Audi");
		nova.setNome(null);
		ResponseEntity<MarcaForm> resposta = marcaController.altera(1L, nova);
		assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
	}

	@Test
	void naoDeveAlterarMarcaInexistente() {
		MarcaForm nova = createMarcaForm("NOVA Toyota");
		ResponseEntity<MarcaForm> resposta = marcaController.altera(1000L, nova);
		assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
	}

	@Test
	void deveDeletarMarcaExistente() throws MarcaAssociadaException {
		ResponseEntity<Marca> resposta = marcaController.deleta(1L);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	void naoDeveDeletarMarcaInexistente() throws MarcaAssociadaException {
		ResponseEntity<Marca> resposta = marcaController.deleta(1000L);
		assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
	}

	@Test
	void deveInformarErroAoDeletarMarcaAssociada() {
		MarcaAssociadaException marcaException = new MarcaAssociadaException("Marca associada");
		ErrosDeValidacaoControllerAdvice erro = new ErrosDeValidacaoControllerAdvice();
		ListaDeErrosOutputDto list = erro.capturaMarcaAssociadaAveiculo(marcaException);

		assertEquals(1, list.getQuantidadeDeErros());
		assertEquals("Marca não removida", list.getErros().get(0).getParametro());
		assertEquals("Marca associada", list.getErros().get(0).getMensagem());

	}

	@Test
	void deveInformarErroNasValidacoesDeCampo() {
		openMocks(this);
		List<FieldError> erros = List.of(new FieldError("marca", "name", "Não pode ser vazio."),
				new FieldError("marca", "nome", "Deve ter pelo menos 2 letras."));
		when(bindingResult.getFieldErrors()).thenReturn(erros);

		MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
		ErrosDeValidacaoControllerAdvice erro = new ErrosDeValidacaoControllerAdvice();
		ListaDeErrosOutputDto list = erro.capturaErrosDeValidacao(exception);

		assertEquals(2, list.getQuantidadeDeErros());
		assertEquals("name", list.getErros().get(0).getParametro());
		assertEquals("Não pode ser vazio.", list.getErros().get(0).getMensagem());

	}

	@Test
	void deveRetornarDashboard() {
		ResponseEntity<List<DashboardForm>> dashBoard = marcaController.dashBoard();
		boolean result = dashBoard.getBody() != null && !dashBoard.getBody().isEmpty() ? true : false;
		assertTrue(result);
	}

}