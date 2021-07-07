package br.com.caelum.carangobom.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.carangobom.domain.Veiculo;

@Configuration
@EnableAutoConfiguration
@ComponentScan("br.com.caelum.carangobom.repository")
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	List<Veiculo> findAllByOrderByModeloAsc();
	
	List<Veiculo> findAllByIsVendido(boolean vendido);
	
	List<Veiculo> findAllByMarcaId(Long marcaId);
	
	List<Veiculo> findAllByMarcaIdAndIsVendido(Long marcaId, boolean vendido);
}
