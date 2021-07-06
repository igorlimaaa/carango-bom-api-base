package br.com.caelum.carangobom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MarcaAssociadaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarcaAssociadaException(String message) {
		super(message);
	}
	
}
