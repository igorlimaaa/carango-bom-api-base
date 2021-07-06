package br.com.caelum.carangobom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.exception.MarcaAssociadaException;
import br.com.caelum.carangobom.form.DashboardForm;
import br.com.caelum.carangobom.form.MarcaForm;

@Service
public interface MarcaService {

	public Marca removeBrand(Long id) throws MarcaAssociadaException;
    
    public MarcaForm updateBrand(Long id, MarcaForm m1);

    public MarcaForm saveBrand(MarcaForm m1);

    public Marca findByIdBrand(Long id);

    public List<MarcaForm> findAllByOrderByNomeBrand();
    
    public List<DashboardForm> findDashboard();

}
