package br.com.caelum.carangobom.form;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class DashboardForm {

	private String nomeMarca;
	private Long qtdeVeiculos;
	private Double valorTotalVeiculos;

}