package br.com.caelum.carangobom.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_marca")
public class Marca {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id_marca;

    @NotBlank
    @Size(min = 2, message = "Deve ter {min} ou mais caracteres.")
    private String ds_nome;

    public Marca() {

    }

    public Marca(String nome) {
        this(null, nome);
    }

    public Marca(Long id, String nome) {
        this.id_marca = id;
        this.ds_nome = nome;
    }

	public Long getId() {
		return id_marca;
	}

	public void setId(Long id_marca) {
		this.id_marca = id_marca;
	}

	public String getNome() {
		return ds_nome;
	}

	public void setNome(String ds_nome) {
		this.ds_nome = ds_nome;
	}

    
}
