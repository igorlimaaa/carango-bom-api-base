package br.com.caelum.carangobom.serviceImpl;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.repository.MarcaRepository;
import br.com.caelum.carangobom.service.MarcaService;
import br.com.caelum.carangobom.validacao.ErroDeParametroOutputDto;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;


@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository marcaRepository;
    

    @Override
    public ResponseEntity<Marca> removeBrand(Long id) {
    	Optional<Marca> m1 = marcaRepository.findById(id);
        if (m1.isPresent()) {
            Marca m2 = m1.get();
            marcaRepository.delete(m2);
            return ResponseEntity.ok(m2);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
	@Override
	public ResponseEntity<Marca> saveBrand(Marca m1, UriComponentsBuilder uriBuilder) {
		 Marca m2 = marcaRepository.save(m1);
	     URI h = uriBuilder.path("/marcas/{id}").buildAndExpand(m1.getId()).toUri();
	     return ResponseEntity.created(h).body(m2);
	}
	
	
	@Override
	public ResponseEntity<Marca> findByIdBrand(Long id) {
		Optional<Marca> m1 = marcaRepository.findById(id);
        if (m1.isPresent()) {
            return ResponseEntity.ok(m1.get());
        } else {
            return ResponseEntity.notFound().build();
        }
	}


	@Override
	public List<Marca> findAllByOrderByNomeBrand() {
		
		//Customizar consulta no repository
		//TODO IAL
		return marcaRepository.findByIdOrderNome();
	}
	
	
	@Override
	public ResponseEntity<Marca> updateBrand(Long id, @Valid Marca m1) {
		Optional<Marca> m2 = marcaRepository.findById(id);
        if (m2.isPresent()) {
            Marca m3 = m2.get();
            m3.setNome(m1.getNome());
            return ResponseEntity.ok(m3);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	
	@Override
	public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao) {
		List<ErroDeParametroOutputDto> l = new ArrayList<>();
        excecao.getBindingResult().getFieldErrors().forEach(e -> {
            ErroDeParametroOutputDto d = new ErroDeParametroOutputDto();
            d.setParametro(e.getField());
            d.setMensagem(e.getDefaultMessage());
            l.add(d);
        });
        ListaDeErrosOutputDto l2 = new ListaDeErrosOutputDto();
        l2.setErros(l);
        return l2;
		
	}
}

