package br.com.caelum.carangobom.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.carangobom.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UsuarioForm {
	
	private Long id;
	private String name;
	private String email;
	private String password;

	public Usuario convertDtoToDomain(UsuarioForm usuarioDto) {
		Usuario usuarioDomain = new Usuario();
		usuarioDomain.setId(usuarioDto.getId());
		usuarioDomain.setEmail(usuarioDto.getEmail());
		usuarioDomain.setNome(usuarioDto.getName());
		usuarioDomain.setSenha(usuarioDto.getPassword());
		return usuarioDomain;
	}
	
	public UsuarioForm convertDomainToDto (Usuario usuarioDomain) {
		UsuarioForm usuarioDto = new UsuarioForm();
		usuarioDto.setId(usuarioDomain.getId());
		usuarioDto.setEmail(usuarioDomain.getEmail());
		usuarioDto.setName(usuarioDomain.getNome());
		usuarioDto.setPassword(usuarioDomain.getSenha());
		return usuarioDto;
	}
	
	public List<UsuarioForm> convertListDomainToDto (List<Usuario> listDomain){
		List<UsuarioForm> listDto = new ArrayList<>();
		listDomain.forEach(userDomain -> {
			UsuarioForm user = new UsuarioForm();
			user.setId(userDomain.getId())	;
			user.setName(userDomain.getNome());
			user.setEmail(userDomain.getEmail());
			listDto.add(user);
		});
		return listDto;
	}

}
