package br.com.caelum.carangobom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.exception.UsuarioExistenteException;
import br.com.caelum.carangobom.form.UsuarioForm;

@Service
public interface UsuarioService {

	
	public UsuarioForm saveUsuario(UsuarioForm u1) throws UsuarioExistenteException;
	
	public List<UsuarioForm> findAllByOrderByNomeBrand();
	
	public UsuarioForm removeUser(Long id);
}
