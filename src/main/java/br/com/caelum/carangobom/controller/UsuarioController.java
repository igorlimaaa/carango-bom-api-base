package br.com.caelum.carangobom.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.carangobom.domain.Usuario;
import br.com.caelum.carangobom.service.UsuarioService;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService mpl;
	
	
	@GetMapping("/usuario")
    @ResponseBody
    @Transactional
    public List<Usuario> lista() {
        return mpl.findAllByOrderByNomeBrand();
    }
	
	@PostMapping("/usuario")
    @ResponseBody
    @Transactional
    public ResponseEntity<Usuario> cadastraUsuario(@Valid @RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {
    	return mpl.saveUsuario(usuario, uriBuilder);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao) {
        return mpl.validacaoUsuario(excecao);
    }
}
