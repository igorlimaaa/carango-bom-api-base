package br.com.caelum.carangobom.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.caelum.carangobom.form.VeiculoForm;
import br.com.caelum.carangobom.service.VeiculoService;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@Controller
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping("/veiculo")
    @ResponseBody
    @Transactional
    public ResponseEntity<List<VeiculoForm>> lista() {
        return new ResponseEntity<>(veiculoService.findAllByOrderByNomeVeiculo(), null, HttpStatus.OK);
    }
	
	@GetMapping("/veiculo/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<VeiculoForm> id(@PathVariable Long id){
		VeiculoForm veiculoForm = veiculoService.findByIdVeiculo(id);
    	if(veiculoForm != null) {
    		return new ResponseEntity<>(veiculoForm, null, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(veiculoForm, null, HttpStatus.NOT_FOUND);    		
    	}
    }
	
	@GetMapping("/veiculo/vendido")
    @ResponseBody
    @Transactional
    public ResponseEntity<List<VeiculoForm>> vedido(){
		 return new ResponseEntity<>(veiculoService.findAllVendido(), null, HttpStatus.OK);
    }


	@PostMapping("/veiculo")
    @ResponseBody
    @Transactional
    public ResponseEntity<VeiculoForm> criarVeiculo(@Valid @RequestBody VeiculoForm veiculoForm) {
		return new ResponseEntity<>(veiculoService.saveVeiculo(veiculoForm), null  , HttpStatus.CREATED);
    }

    @PutMapping("/veiculo/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<VeiculoForm> updateVeiculo(@PathVariable Long id, @Valid @RequestBody VeiculoForm v1){
    	VeiculoForm veiculo = veiculoService.updateVeiculo(id, v1);
    	if(veiculo != null) {
    		return new ResponseEntity<>(veiculo, null, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(veiculo, null, HttpStatus.NOT_FOUND);    		
    	}
    }
    
    
    @DeleteMapping("/veiculo/{id}")
	@ResponseBody
	@Transactional
	@Validated
	public ResponseEntity<VeiculoForm> deleta(@PathVariable Long id) {
    	VeiculoForm veiculo = veiculoService.removeVeiculo(id);
		if (veiculo != null) {
			return new ResponseEntity<VeiculoForm>(veiculo, null, HttpStatus.OK);
		}else {
			return new ResponseEntity<VeiculoForm>(veiculo, null, HttpStatus.NOT_FOUND);			
		}

	}
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao) {
        return veiculoService.validacao(excecao);
    }

}