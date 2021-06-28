package br.com.caelum.carangobom.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.carangobom.domain.Usuario;

@Component
public class UsuarioForm {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario convertDtoToDomain(UsuarioForm usuarioDto) {
		Usuario usuarioDomain = new Usuario();
		usuarioDomain.setId_usuario(usuarioDto.getId());
		usuarioDomain.setDs_email(usuarioDto.getEmail());
		usuarioDomain.setDs_nome(usuarioDto.getNome());
		usuarioDomain.setDs_senha(usuarioDto.getSenha());
		return usuarioDomain;
	}
	
	public UsuarioForm convertDomainToDto (Usuario usuarioDomain) {
		UsuarioForm usuarioDto = new UsuarioForm();
		usuarioDto.setId(usuarioDomain.getId_usuario());
		usuarioDto.setEmail(usuarioDomain.getDs_email());
		usuarioDto.setNome(usuarioDomain.getDs_nome());
		usuarioDto.setSenha(usuarioDomain.getDs_senha());
		return usuarioDto;
	}
	
	public List<UsuarioForm> convertListDomainToDto (List<Usuario> listDomain){
		List<UsuarioForm> listDto = new ArrayList<>();
		listDomain.forEach(userDomain -> {
			UsuarioForm user = new UsuarioForm();
			user.setId(userDomain.getId_usuario());
			user.setNome(userDomain.getDs_nome());
			user.setEmail(userDomain.getDs_email());
			listDto.add(user);
		});
		return listDto;
	}

}
