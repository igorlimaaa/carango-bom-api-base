package br.com.caelum.carangobom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.caelum.carangobom.domain.Usuario;

@Configuration
@EnableAutoConfiguration
@ComponentScan("br.com.caelum.carangobom.repository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value = "SELECT m.* FROM tb_usuario m WHERE ds_email = :email", nativeQuery = true)
	Optional<Usuario> findByEmail(String email);
	
	@Query(value = "SELECT m.* FROM tb_usuario m ORDER BY ds_nome", nativeQuery = true)
	List<Usuario> findByIdOrderNome();
	
}
