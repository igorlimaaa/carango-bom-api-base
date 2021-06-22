package br.com.caelum.carangobom.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.carangobom.domain.Usuario;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@Service
public interface UsuarioService {

	
	public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario u1, UriComponentsBuilder uriBuilder);
	
	public ListaDeErrosOutputDto validacaoUsuario(MethodArgumentNotValidException excecao);
	
	public List<Usuario> findAllByOrderByNomeBrand();
}
