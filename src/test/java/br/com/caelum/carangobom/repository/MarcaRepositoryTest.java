package br.com.caelum.carangobom.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.caelum.carangobom.domain.Marca;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MarcaRepositoryTest {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	private void _configureListMarca() {
		List<Marca> listMarca = new ArrayList<>();
		listMarca.add(new Marca("Audi"));
		listMarca.add(new Marca("Ford"));
		listMarca.add(new Marca("Hyndai"));
		marcaRepository.saveAll(listMarca);
	}
	
	@Test
	public void deveRetornarListaMarcas() {
		_configureListMarca();
		List<Marca> listMarca = marcaRepository.findByIdOrderNome();
		boolean result = listMarca != null && !listMarca.isEmpty() ? true : false;
		Assert.assertTrue(result);
	}

}
