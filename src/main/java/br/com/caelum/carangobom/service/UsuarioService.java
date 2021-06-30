package br.com.caelum.carangobom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.caelum.carangobom.form.UsuarioForm;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@Service
public interface UsuarioService {

	
	public UsuarioForm saveUsuario(UsuarioForm u1);
	
	public List<UsuarioForm> findAllByOrderByNomeBrand();
	
	public ListaDeErrosOutputDto validacaoUsuario(MethodArgumentNotValidException excecao);
	
	public UsuarioForm removeUser(Long id);
}
