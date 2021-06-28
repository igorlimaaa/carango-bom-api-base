package br.com.caelum.carangobom.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;
	private String ds_nome;
	private String ds_email;
	private String ds_senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
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

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.ds_senha;
	}

	@Override
	public String getUsername() {
		return this.ds_email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
