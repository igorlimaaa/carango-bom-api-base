package br.com.caelum.carangobom.form;

import org.springframework.stereotype.Component;

import br.com.caelum.carangobom.domain.Usuario;

@Component
public class UsuarioForm {
	
	private Long id_usuario;
	private String ds_nome;
	private String ds_email;
	private String ds_senha;
	
	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getDs_nome() {
		return ds_nome;
	}

	public void setDs_nome(String ds_nome) {
		this.ds_nome = ds_nome;
	}

	public String getDs_email() {
		return ds_email;
	}

	public void setDs_email(String ds_email) {
		this.ds_email = ds_email;
	}

	public String getDs_senha() {
		return ds_senha;
	}

	public void setDs_senha(String ds_senha) {
		this.ds_senha = ds_senha;
	}

	public Usuario convertDtoToDomain(UsuarioForm usuarioDto) {
		Usuario usuarioDomain = new Usuario();
		usuarioDomain.setId(usuarioDto.getId_usuario());
		usuarioDomain.setEmail(usuarioDto.getDs_email());
		usuarioDomain.setNome(usuarioDto.getDs_nome());
		usuarioDomain.setSenha(usuarioDto.getDs_senha());
		return usuarioDomain;
	}
	
	public UsuarioForm convertDomainToDto (Usuario usuarioDomain) {
		UsuarioForm usuarioDto = new UsuarioForm();
		usuarioDto.setId_usuario(usuarioDomain.getId());
		usuarioDto.setDs_email(usuarioDomain.getEmail());
		usuarioDto.setDs_nome(usuarioDomain.getNome());
		usuarioDto.setDs_senha(usuarioDomain.getSenha());
		return usuarioDto;
	}

}
