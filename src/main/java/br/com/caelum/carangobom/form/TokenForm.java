package br.com.caelum.carangobom.form;

public class TokenForm {
	
	private String token;
	private String tipo;
	
	public TokenForm(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	

}
