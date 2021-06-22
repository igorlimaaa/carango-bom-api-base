package br.com.caelum.carangobom.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.domain.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${caramgo.jwt.expiration}")
	private String experation;
	
	@Value("${caramgo.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		
		Usuario login = (Usuario) authentication.getPrincipal();
		Date now = new Date();
		Date dateEx = new Date(now.getTime() + Long.parseLong(experation));
		
		
		return Jwts.builder()
				.setIssuer("API Carango Bom")
				.setSubject(login.getId().toString())
				.setIssuedAt(now)
				.setExpiration(dateEx)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
