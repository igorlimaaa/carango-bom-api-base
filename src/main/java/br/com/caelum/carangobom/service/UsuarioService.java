package br.com.caelum.carangobom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.caelum.carangobom.domain.Usuario;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@Service
public interface UsuarioService {

	
	public Usuario saveUsuario(Usuario u1);
	
	public List<Usuario> findAllByOrderByNomeBrand();
	
	public ListaDeErrosOutputDto validacaoUsuario(MethodArgumentNotValidException excecao);
}
