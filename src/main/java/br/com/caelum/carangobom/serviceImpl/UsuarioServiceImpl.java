package br.com.caelum.carangobom.serviceImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.caelum.carangobom.domain.Usuario;
import br.com.caelum.carangobom.repository.UsuarioRepository;
import br.com.caelum.carangobom.service.UsuarioService;
import br.com.caelum.carangobom.validacao.ErroDeParametroOutputDto;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public ResponseEntity<Usuario> saveUsuario(@Valid Usuario u1, UriComponentsBuilder uriBuilder) {
		u1.setSenha(new BCryptPasswordEncoder().encode(u1.getSenha()));
		Usuario u2 = userRepository.save(u1);
		URI h = uriBuilder.path("/usuario/{id}").buildAndExpand(u1.getId()).toUri();
		return ResponseEntity.created(h).body(u2);
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
		return userRepository.findByIdOrderNome();
	}
	
}
