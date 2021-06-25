package br.com.caelum.carangobom.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.caelum.carangobom.domain.Usuario;
import br.com.caelum.carangobom.form.UsuarioForm;
import br.com.caelum.carangobom.repository.UsuarioRepository;
import br.com.caelum.carangobom.service.UsuarioService;
import br.com.caelum.carangobom.validacao.ErroDeParametroOutputDto;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private UsuarioForm usuarioForm;
	
	@Override
	public UsuarioForm saveUsuario(UsuarioForm usuarioType) {
		usuarioType.setSenha(new BCryptPasswordEncoder().encode(usuarioType.getSenha()));
		Usuario usuarioSave = userRepository.save(usuarioForm.convertDtoToDomain(usuarioType)); 
		return usuarioForm.convertDomainToDto(usuarioSave);
	}
	
	@Override
	public ListaDeErrosOutputDto validacaoUsuario(MethodArgumentNotValidException excecao) {
		List<ErroDeParametroOutputDto> l = new ArrayList<>();
        excecao.getBindingResult().getFieldErrors().forEach(e -> {
            ErroDeParametroOutputDto d = new ErroDeParametroOutputDto();
            d.setParametro(e.getField());
            d.setMensagem(e.getDefaultMessage());
            l.add(d);
        });
        ListaDeErrosOutputDto l2 = new ListaDeErrosOutputDto();
        l2.setErros(l);
        return l2;
		
	}

	@Override
	public List<Usuario> findAllByOrderByNomeBrand() {
		List<Usuario> listUsuario = userRepository.findByIdOrderNome();
		listUsuario.forEach(listaUsuario -> listaUsuario.setSenha(null));
		return listUsuario;
	}
	
}
