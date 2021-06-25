package br.com.caelum.carangobom.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.caelum.carangobom.domain.Marca;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MarcaRepositoryTest {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Test
	public void deveRetornarListaMarcas() {
		List<Marca> listMarca = marcaRepository.findByIdOrderNome();
		boolean result = listMarca != null && !listMarca.isEmpty() ? true : false;
		Assert.assertTrue(result);
	}

}
