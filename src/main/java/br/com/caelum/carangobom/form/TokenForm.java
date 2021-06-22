package br.com.caelum.carangobom.form;

public class TokenForm {
	
	private String token;
	private String tipo;
	
	public TokenForm(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	private String getToken() {
		return token;
	}

	private String getTipo() {
		return tipo;
	}
	

}
