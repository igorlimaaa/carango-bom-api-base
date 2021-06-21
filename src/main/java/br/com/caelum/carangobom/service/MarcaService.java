package br.com.caelum.carangobom.service;

import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import javax.validation.Valid;

@Service
public interface MarcaService {

	public ResponseEntity<Marca> removeBrand(Long id);
    
    public ResponseEntity<Marca> updateBrand(@PathVariable Long id, @Valid @RequestBody Marca m1);

    public ResponseEntity<Marca> saveBrand(@Valid @RequestBody Marca m1, UriComponentsBuilder uriBuilder);

    public ResponseEntity<Marca> findByIdBrand(Long id);

    public List<Marca> findAllByOrderByNomeBrand();
    
    public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao);

}
