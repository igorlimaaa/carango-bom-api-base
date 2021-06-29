package br.com.caelum.carangobom.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.caelum.carangobom.domain.Marca;

//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//public class MarcaRepositoryTest {
//	
//	@Autowired
//	private MarcaRepository marcaRepository;
//	
//	@Test
//	public void deveRetornarListaMarcas() {
//		List<Marca> listMarca = marcaRepository.findByIdOrderNome();
//		boolean result = listMarca != null && !listMarca.isEmpty() ? true : false;
//		assertTrue(result);
//	}
//
//}
