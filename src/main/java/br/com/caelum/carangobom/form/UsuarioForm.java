package br.com.caelum.carangobom.form;

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
		usuarioDomain.setId(usuarioDto.getId());
		usuarioDomain.setEmail(usuarioDto.getEmail());
		usuarioDomain.setNome(usuarioDto.getNome());
		usuarioDomain.setSenha(usuarioDto.getSenha());
		return usuarioDomain;
	}
	
	public UsuarioForm convertDomainToDto (Usuario usuarioDomain) {
		UsuarioForm usuarioDto = new UsuarioForm();
		usuarioDto.setId(usuarioDomain.getId());
		usuarioDto.setEmail(usuarioDomain.getEmail());
		usuarioDto.setNome(usuarioDomain.getNome());
		usuarioDto.setSenha(usuarioDomain.getSenha());
		return usuarioDto;
	}

}
