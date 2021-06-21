package br.com.caelum.carangobom.controller;

import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.service.MarcaService;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

import javax.validation.Valid;

@Controller
public class MarcaController {
	
	@Autowired
    private MarcaService mpl;
	private @Valid Marca m1;
    
    @Autowired
    public MarcaController(MarcaService mpl) {
        this.mpl = mpl;
    }

    @GetMapping("/marcas")
    @ResponseBody
    @Transactional
    public List<Marca> lista() {
        return mpl.findAllByOrderByNomeBrand();
    }

    @GetMapping("/marcas/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Marca> id(@PathVariable Long id) {
        return mpl.findByIdBrand(id);
    }
    

    @PostMapping("/marcas")
    @ResponseBody
    @Transactional
    public ResponseEntity<Marca> cadastra(@Valid @RequestBody Marca marca, UriComponentsBuilder uriBuilder) {
    	return mpl.saveBrand(marca, uriBuilder);
    }

    @PutMapping("/marcas/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Marca> altera(@PathVariable Long id, @Valid @RequestBody Marca m1) {
    	return mpl.updateBrand(id, m1);
    }

    @DeleteMapping("/marcas/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Marca> deleta(@PathVariable Long id) {
    	return mpl.removeBrand(id);
        
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao) {
        return mpl.validacao(excecao);
    }
    
}