package br.com.caelum.carangobom.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @NotNull
    private Long ano;
    
    @NotNull
    private String modelo;
    
    @NotNull
    private Double preco;
    
    @Column(columnDefinition="boolean default true")
    private boolean isVendido;
    
    @ManyToOne()
    @JoinColumn(name="marca_id", nullable = false)
    private Marca marca;
    

    public Veiculo() {

    }

    public Veiculo(Long id, Long ano ,String modelo, Double preco, boolean isVendido, Marca marca) {
        this.id = id;
        this.ano = ano;
        this.modelo = modelo;
        this.preco = preco;
        this.isVendido = isVendido;
        this.marca = marca;
        
    }
    
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
    
    public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
    
}
