package br.com.caelum.carangobom.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.domain.Veiculo;

@Component
public class VeiculoForm {
	    
	
		private Long id;
	    
		private String modelo;
	    
	    private Long ano;
	    
	    private Double preco;
	    
	    private boolean isVendido; 
	    
	    private MarcaForm marca;

		
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getAno() {
			return ano;
		}

		public void setAno(Long ano) {
			this.ano = ano;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		
		public Double getPreco() {
			return preco;
		}

		public void setPreco(Double preco) {
			this.preco = preco;
		}
		
		
		public boolean getIsVendido() {
			return isVendido;
		}

		public void setIsVendido(boolean isVendido) {
			this.isVendido = isVendido;
		}
		
		public MarcaForm getMarca() {
			return marca;
		}

		public void setMarca(MarcaForm marca) {
			this.marca = marca;
		}
		
		
		public List<VeiculoForm> convertDomainToDto(List<Veiculo> veiculo) {
			List<VeiculoForm> listaType = new ArrayList<>();
			veiculo.forEach(veiculoDomain -> {
				VeiculoForm veiculoType = new VeiculoForm();
				veiculoType.setId(veiculoDomain.getId());
				veiculoType.setAno(veiculoDomain.getAno());
				veiculoType.setModelo(veiculoDomain.getModelo());
				veiculoType.setPreco(veiculoDomain.getPreco());
				veiculoType.setIsVendido(veiculoDomain.getIsVendido());
				
				MarcaForm marca = new MarcaForm();
				marca.setId(veiculoDomain.getMarca().getId());
				marca.setNome(veiculoDomain.getMarca().getNome());
				veiculoType.setMarca(marca);
			
				listaType.add(veiculoType);
				
			});
			
			return listaType;
		}
		
		public Veiculo convertTypeToDomain(VeiculoForm veiculoType) {
			Veiculo veiculoDomain = new Veiculo();
			veiculoDomain.setId(veiculoType.getId());
			veiculoDomain.setAno(veiculoType.getAno());
			veiculoDomain.setModelo(veiculoType.getModelo());
			veiculoDomain.setPreco(veiculoType.getPreco());
			veiculoDomain.setIsVendido(veiculoType.getIsVendido());
			
			Marca marca = new Marca();
			marca.setId(veiculoType.getMarca().getId());
			veiculoDomain.setMarca(marca);
			
			return veiculoDomain;
		}
		
		public VeiculoForm convertDomainToType(Veiculo veiculoDomain) {
			VeiculoForm veiculoType = new VeiculoForm();
			veiculoType.setId(veiculoDomain.getId());
			veiculoType.setAno(veiculoDomain.getAno());
			veiculoType.setModelo(veiculoDomain.getModelo());
			veiculoType.setPreco(veiculoDomain.getPreco());
			veiculoType.setIsVendido(veiculoDomain.getIsVendido());
			
			MarcaForm marca = new MarcaForm();
			marca.setId(veiculoDomain.getMarca().getId());
			marca.setNome(veiculoDomain.getMarca().getNome());
			veiculoType.setMarca(marca);
		
			return veiculoType;
		}

}
