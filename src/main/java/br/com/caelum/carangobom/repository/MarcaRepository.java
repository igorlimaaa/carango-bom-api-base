package br.com.caelum.carangobom.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.caelum.carangobom.domain.Marca;


@Configuration
@EnableAutoConfiguration
@ComponentScan("br.com.caelum.carangobom.repository")
public interface MarcaRepository extends JpaRepository<Marca, Integer>{
	
	@Query(value = "SELECT m.* FROM marca m ORDER BY nome", nativeQuery = true)
	List<Marca> findByIdOrderNome();
	
}
