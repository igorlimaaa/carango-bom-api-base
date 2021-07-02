package br.com.caelum.carangobom.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.caelum.carangobom.domain.Marca;
import br.com.caelum.carangobom.domain.Veiculo;
import br.com.caelum.carangobom.form.DashboardForm;
import br.com.caelum.carangobom.form.MarcaForm;
import br.com.caelum.carangobom.repository.MarcaRepository;
import br.com.caelum.carangobom.repository.VeiculoRepository;
import br.com.caelum.carangobom.service.MarcaService;
import br.com.caelum.carangobom.validacao.ErroDeParametroOutputDto;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;


@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private MarcaForm marcaForm;

    @Override
    public Marca removeBrand(Long id) {
    	Optional<Marca> m1 = marcaRepository.findById(id);
        if (m1.isPresent()) {
            Marca m2 = m1.get();
            marcaRepository.delete(m2);
            return m2;
        } else {
            return null;
        }
    }
    
    
	@Override
	public MarcaForm saveBrand(MarcaForm marcaType) {
		Marca marcaSave = marcaRepository.save(marcaForm.convertTypeToDomain(marcaType));
		return marcaForm.convertDomainToType(marcaSave);
	}
	
	
	@Override
	public Marca findByIdBrand(Long id) {
		Optional<Marca> m1 = marcaRepository.findById(id);
        if (m1.isPresent()) {
            return m1.get();
        } else {
            return null;
        }
	}


	@Override
	public List<MarcaForm> findAllByOrderByNomeBrand() {
		List<Marca> listMarca = marcaRepository.findByIdOrderNome();
		return marcaForm.convertDomainToDto(listMarca);
	}
	
	
	@Override
	public MarcaForm updateBrand(Long id,MarcaForm marcaType) {
		Optional<Marca> marcaDomain = marcaRepository.findById(id);
        if (marcaDomain.isPresent() && marcaType.getNome() != null) {
            Marca marcaSave = marcaDomain.get();
        	marcaSave.setNome(marcaType.getNome());
            return marcaForm.convertDomainToType(marcaRepository.saveAndFlush(marcaSave));
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
	public List<DashboardForm> findDashboard() {
		List<DashboardForm> listBoard = new ArrayList<>();
		List<Marca> listMarcas = marcaRepository.findAll();
		listMarcas.forEach(marca -> {
			DashboardForm dashboard = new DashboardForm();
			dashboard.setNomeMarca(marca.getNome());
			listBoard.add(createMarcaDashboard(marca, dashboard));
		});
		return listBoard;
	}
	
	private DashboardForm createMarcaDashboard(Marca marca, DashboardForm dashboard) {
		Optional<List<Veiculo>> findVeiculosByMarcaId = veiculoRepository.findVeiculosByMarcaId(marca.getId().intValue());
		if(findVeiculosByMarcaId.isPresent()) {
			List<Veiculo> listVeiculo = findVeiculosByMarcaId.get();
			dashboard.setQtdeVeiculos(extractQtdeVeiculoMarca(listVeiculo));
			dashboard.setValorTotalVeiculos(extractValorVeiculosMarca(listVeiculo));
		}
		return dashboard;
	}
	
	private Double extractValorVeiculosMarca(List<Veiculo> listVeiculo) {
		Double valorSomado = 0.0;
		if(!listVeiculo.isEmpty()) {
			for(Veiculo veiculo : listVeiculo) {
				valorSomado += veiculo.getPreco();
			}
			return valorSomado;				
		}
		return valorSomado;
	}
	
	private Long extractQtdeVeiculoMarca(List<Veiculo> listVeiculo) {
		if(!listVeiculo.isEmpty()) {
			return (long) listVeiculo.size();
		}
		return 0L;
	}
}

