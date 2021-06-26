package br.com.caelum.carangobom.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.carangobom.domain.Marca;

@Component
public class MarcaForm {
	
	private Long id_marca;
	private String ds_nome;
	
	public Long getId_marca() {
		return id_marca;
	}

	public void setId_marca(Long id_marca) {
		this.id_marca = id_marca;
	}

	public String getDs_nome() {
		return ds_nome;
	}

	public void setDs_nome(String ds_nome) {
		this.ds_nome = ds_nome;
	}

	public List<MarcaForm> convertDomainToDto(List<Marca> marca) {
		List<MarcaForm> listaType = new ArrayList<>();
		marca.forEach(marcaDomain -> {
			MarcaForm marcaType = new MarcaForm();
			marcaType.setId_marca(marcaDomain.getId());
			marcaType.setDs_nome(marcaDomain.getNome());
			listaType.add(marcaType);
		});
		
		return listaType;
	}
	
	public Marca convertTypeToDomain(MarcaForm marcaType) {
		Marca marcaDomain = new Marca();
		marcaDomain.setId(null);
		marcaDomain.setNome(marcaType.getDs_nome());
		return marcaDomain;
	}
	
	public MarcaForm convertDomainToType(Marca marcaDomain) {
		MarcaForm marcaType = new MarcaForm();
		marcaType.setId_marca(marcaDomain.getId());
		marcaType.setDs_nome(marcaDomain.getNome());
		return marcaType;
	}

}
