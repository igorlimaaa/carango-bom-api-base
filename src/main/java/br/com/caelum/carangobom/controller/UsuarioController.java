package br.com.caelum.carangobom.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.carangobom.exception.UsuarioExistenteException;
import br.com.caelum.carangobom.form.UsuarioForm;
import br.com.caelum.carangobom.service.UsuarioService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/usuario")
    @ResponseBody
    @Transactional
    public List<UsuarioForm> lista() {
        return usuarioService.findAllByOrderByNomeBrand();
    }
	
	@PostMapping("/usuario")
    @ResponseBody
    @Transactional
    public ResponseEntity<UsuarioForm> cadastraUsuario(@Valid @RequestBody UsuarioForm usuario) throws UsuarioExistenteException {
    	return new ResponseEntity<>(usuarioService.saveUsuario(usuario), null, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/usuario/{id}")
	@ResponseBody
	@Transactional
	@Validated
	public ResponseEntity<UsuarioForm> deleta(@PathVariable Long id) {
		UsuarioForm usuario = usuarioService.removeUser(id);
		if (usuario != null) {
			return new ResponseEntity<>(usuario, null, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(usuario, null, HttpStatus.NOT_FOUND);			
		}

	}
}
