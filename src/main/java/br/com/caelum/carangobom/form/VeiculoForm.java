package br.com.caelum.carangobom.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.domain.Veiculo;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class VeiculoForm {

	private Long id;

	private String modelo;

	private Long ano;

	private Double preco;

	private Boolean isVendido;

	private MarcaForm marca;

	public List<VeiculoForm> convertDomainToDto(List<Veiculo> veiculo) {
		List<VeiculoForm> listaType = new ArrayList<>();
		veiculo.forEach(veiculoDomain -> listaType.add(convertDomainToType(veiculoDomain)));
		return listaType;
	}

	public VeiculoForm convertDomainToType(Veiculo veiculoDomain) {
		VeiculoForm veiculoType = new VeiculoForm();
		veiculoType.setId(veiculoDomain.getId());
		veiculoType.setAno(veiculoDomain.getAno());
		veiculoType.setModelo(veiculoDomain.getModelo());
		veiculoType.setPreco(veiculoDomain.getPreco());
		veiculoType.setIsVendido(veiculoDomain.getIsVendido());

		MarcaForm marcaDomain = new MarcaForm();
		marcaDomain.setId(veiculoDomain.getMarca().getId());
		marcaDomain.setNome(veiculoDomain.getMarca().getNome());
		veiculoType.setMarca(marcaDomain);

		return veiculoType;
	}

	public Veiculo convertTypeToDomain(VeiculoForm veiculoType) {
		Veiculo veiculoDomain = new Veiculo();
		veiculoDomain.setId(veiculoType.getId());
		veiculoDomain.setAno(veiculoType.getAno());
		veiculoDomain.setModelo(veiculoType.getModelo());
		veiculoDomain.setPreco(veiculoType.getPreco());
		veiculoDomain.setIsVendido(veiculoType.getIsVendido());

		Marca marcaDomain = new Marca();
		marcaDomain.setId(veiculoType.getMarca().getId());
		veiculoDomain.setMarca(marcaDomain);

		return veiculoDomain;
	}

}
