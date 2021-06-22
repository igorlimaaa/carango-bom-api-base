package br.com.caelum.carangobom.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.caelum.carangobom.domain.Usuario;
import br.com.caelum.carangobom.repository.UsuarioRepository;

public class AuthenticationToken extends OncePerRequestFilter{
	
	
	
	private TokenService tokenService;
	private UsuarioRepository repository;
	
	
	public AuthenticationToken(TokenService tokenService,  UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.repository = usuarioRepository;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valid = tokenService.isTokenValid(token);
		
		if(valid) {
			authenClient(token);
		}
		
		
		filterChain.doFilter(request, response);
		
	}

	private void authenClient(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = repository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}



	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
}
