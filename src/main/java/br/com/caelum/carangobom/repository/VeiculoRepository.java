package br.com.caelum.carangobom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.caelum.carangobom.domain.Veiculo;

@Configuration
@EnableAutoConfiguration
@ComponentScan("br.com.caelum.carangobom.repository")
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	@Query(value = "SELECT v.* FROM veiculo v ORDER BY modelo", nativeQuery = true)
	List<Veiculo> findByIdOrderModeloVeiculo();
	
	@Query(value = "SELECT v.* FROM veiculo v where is_vendido = false", nativeQuery = true)
	List<Veiculo> findByIdVedido();
	
	@Query(value = "SELECT v.* FROM veiculo v where marca_id = :marcaId and is_vendido = false", nativeQuery = true)
	Optional<List<Veiculo>> findVeiculosByMarcaId(int marcaId);
}
