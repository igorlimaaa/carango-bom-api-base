package br.com.caelum.carangobom.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.domain.Usuario;
import br.com.caelum.carangobom.exception.UsuarioExistenteException;
import br.com.caelum.carangobom.form.UsuarioForm;
import br.com.caelum.carangobom.repository.UsuarioRepository;
import br.com.caelum.carangobom.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private UsuarioForm usuarioForm;

	@Override
	public UsuarioForm saveUsuario(UsuarioForm usuarioType) throws UsuarioExistenteException{
		Optional<Usuario> usuarioExistente = userRepository.findByEmail(usuarioType.getEmail());
		if(usuarioExistente.isPresent()) {
			throw new UsuarioExistenteException("E-mail já associado a um usuário");
		}
		usuarioType.setPassword(new BCryptPasswordEncoder().encode(usuarioType.getPassword()));
		Usuario usuarioSave = userRepository.save(usuarioForm.convertDtoToDomain(usuarioType));
		return usuarioForm.convertDomainToDto(usuarioSave);
	}

	@Override
	public List<UsuarioForm> findAllByOrderByNomeBrand() {
		List<Usuario> listUsuario = userRepository.findAllByOrderByNomeAsc();
		return usuarioForm.convertListDomainToDto(listUsuario);
	}

	@Override
	public UsuarioForm removeUser(Long id) {
		Optional<Usuario> u1 = userRepository.findById(id);
		if (u1.isPresent()) {
			Usuario u2 = u1.get();
			userRepository.delete(u2);
			return usuarioForm.convertDomainToDto(u2);
		} else {
			return null;
		}
	}

}
