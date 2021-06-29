package br.com.caelum.carangobom.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.caelum.carangobom.form.VeiculoForm;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@Service
public interface VeiculoService {

    public VeiculoForm updateVeiculo(Long id, VeiculoForm veiculoForm);

    public VeiculoForm saveVeiculo(VeiculoForm veiculoForm );
    
    public ListaDeErrosOutputDto validacao(MethodArgumentNotValidException excecao);
    
    public List<VeiculoForm> findAllByOrderByNomeVeiculo();

}
