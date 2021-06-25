package br.com.caelum.carangobom.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.form.MarcaForm;
import br.com.caelum.carangobom.service.MarcaService;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@Controller
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;

    @GetMapping("/marcas")
    @ResponseBody
    @Transactional
    @Cacheable(value = "listaMarcas")
    public ResponseEntity<List<MarcaForm>> lista() {
        return new ResponseEntity<List<MarcaForm>>(marcaService.findAllByOrderByNomeBrand(), null, HttpStatus.OK);
    }

    @GetMapping("/marcas/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Marca> id(@PathVariable Long id){
    	Marca marca = marcaService.findByIdBrand(id);
    	if(marca != null) {
    		return new ResponseEntity<Marca>(marca, null, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<Marca>(marca, null, HttpStatus.NOT_FOUND);    		
    	}
    }
    

    @PostMapping("/marcas")
    @ResponseBody
    @Transactional
    @CacheEvict(value = "listaMarcas", allEntries = true)
    public ResponseEntity<MarcaForm> cadastra(@Valid @RequestBody MarcaForm marca) {
    	return new ResponseEntity<MarcaForm>(marcaService.saveBrand(marca), HttpStatus.CREATED);
    }

    @PutMapping("/marcas/{id}")
    @ResponseBody
    @Transactional
    @CacheEvict(value = "listaMarcas", allEntries = true)
    public ResponseEntity<MarcaForm> altera(@PathVariable Long id, @Valid @RequestBody MarcaForm m1){
    	MarcaForm marca = marcaService.updateBrand(id, m1);
    	if(marca != null) {
    		return new ResponseEntity<MarcaForm>(marca, null, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<MarcaForm>(marca, null, HttpStatus.NOT_FOUND);    		
    	}
    }

	@DeleteMapping("/marcas/{id}")
	@ResponseBody
	@Transactional
	@Validated
	@CacheEvict(value = "listaMarcas", allEntries = true)
	public ResponseEntity<Marca> deleta(@PathVariable Long id) {
		Marca marca = marcaService.removeBrand(id);
		if (marca != null) {
			return new ResponseEntity<Marca>(marca, null, HttpStatus.OK);
		}else {
			return new ResponseEntity<Marca>(marca, null, HttpStatus.NOT_FOUND);			
		}

	}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao) {
        return marcaService.validacao(excecao);
    }
    
}