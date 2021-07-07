package br.com.caelum.carangobom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.carangobom.domain.Usuario;

@Configuration
@EnableAutoConfiguration
@ComponentScan("br.com.caelum.carangobom.repository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByEmail(String email);
	
	public List<Usuario> findAllByOrderByNomeAsc();
	
}
