package br.com.caelum.carangobom.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.carangobom.domain.Marca;

@Component
public class MarcaForm {
	
	private Long id;
	private String nome;
	
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
	
	public List<MarcaForm> convertDomainToDto(List<Marca> marca) {
		List<MarcaForm> listaType = new ArrayList<>();
		marca.forEach(marcaDomain -> {
			MarcaForm marcaType = new MarcaForm();
			marcaType.setId(marcaDomain.getId());
			marcaType.setNome(marcaDomain.getNome());
			listaType.add(marcaType);
		});
		
		return listaType;
	}
	
	public Marca convertTypeToDomain(MarcaForm marcaType) {
		Marca marcaDomain = new Marca();
		marcaDomain.setId(null);
		marcaDomain.setNome(marcaType.getNome());
		return marcaDomain;
	}
	
	public MarcaForm convertDomainToType(Marca marcaDomain) {
		MarcaForm marcaType = new MarcaForm();
		marcaType.setId(marcaDomain.getId());
		marcaType.setNome(marcaDomain.getNome());
		return marcaType;
	}

}
