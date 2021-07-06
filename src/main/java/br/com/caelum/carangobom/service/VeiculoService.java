package br.com.caelum.carangobom.service;


import java.util.List;

import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.form.VeiculoForm;

@Service
public interface VeiculoService {

    public VeiculoForm updateVeiculo(Long id, VeiculoForm veiculoForm);
    
    public VeiculoForm removeVeiculo(Long id);

    public VeiculoForm saveVeiculo(VeiculoForm veiculoForm );
    
    public List<VeiculoForm> findAllByOrderByNomeVeiculo();
    
    public VeiculoForm findByIdVeiculo(Long id);
    
    public List<VeiculoForm> findAllVendido();

}
