package br.com.caelum.carangobom.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.carangobom.domain.Marca;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class MarcaForm {

	private Long id;
	private String nome;

	public List<MarcaForm> convertDomainToDto(List<Marca> marca) {
		List<MarcaForm> listaType = new ArrayList<>();
		marca.forEach(marcaDomain -> listaType.add(convertDomainToType(marcaDomain)));
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
