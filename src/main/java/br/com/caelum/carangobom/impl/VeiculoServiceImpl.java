package br.com.caelum.carangobom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.caelum.carangobom.domain.Veiculo;
import br.com.caelum.carangobom.form.VeiculoForm;
import br.com.caelum.carangobom.repository.VeiculoRepository;
import br.com.caelum.carangobom.service.VeiculoService;
import br.com.caelum.carangobom.validacao.ErroDeParametroOutputDto;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;


@Service
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private VeiculoForm veiculoForm;
	
	
	@Override
	public VeiculoForm saveVeiculo(VeiculoForm veiculoType) {
		Veiculo veiculoSave = veiculoRepository.save(veiculoForm.convertTypeToDomain(veiculoType));
		return veiculoForm.convertDomainToType(veiculoSave);
	}
	

	@Override
	public VeiculoForm updateVeiculo(Long id, VeiculoForm veiculoForm) {
		Optional<Veiculo> veiculoDomain = veiculoRepository.findById(id);
        if (veiculoDomain.isPresent() && veiculoDomain.get() != null) {
            Veiculo veiculoSave = veiculoDomain.get();
        	veiculoSave.setModelo(veiculoForm.getModelo());
            return veiculoForm.convertDomainToType(veiculoRepository.saveAndFlush(veiculoSave));
        } else {
            return null;
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


	@Override
	public List<VeiculoForm> findAllByOrderByNomeVeiculo() {
		List<Veiculo> listMarca = veiculoRepository.findByIdOrderModeloVeiculo();
		return veiculoForm.convertDomainToDto(listMarca);
	}
}

