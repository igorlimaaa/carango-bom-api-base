package br.com.caelum.carangobom.domain;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_perfil")
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_perfil;
	
	private String ds_nome;

	@Override
	public String getAuthority() {
		return ds_nome;
	}
	
}
