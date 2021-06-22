package br.com.caelum.carangobom.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.carangobom.config.security.TokenService;
import br.com.caelum.carangobom.form.LoginForm;
import br.com.caelum.carangobom.form.TokenForm;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService; 
	
	
	@PostMapping
	public ResponseEntity<TokenForm> authentication (@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosUser = form.converter();
		
		try {
			Authentication authentication =  authManager.authenticate(dadosUser);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenForm(token, "Bearer"));
		}
		catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	

}
