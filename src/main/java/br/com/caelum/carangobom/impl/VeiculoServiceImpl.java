package br.com.caelum.carangobom.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.domain.Veiculo;
import br.com.caelum.carangobom.form.VeiculoForm;
import br.com.caelum.carangobom.repository.VeiculoRepository;
import br.com.caelum.carangobom.service.VeiculoService;

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
		if (veiculoDomain.isPresent()) {
			Veiculo veiculoSave = veiculoDomain.get();
			veiculoSave.setModelo(veiculoForm.getModelo());
			return veiculoForm.convertDomainToType(veiculoRepository.saveAndFlush(veiculoSave));
		} else {
			return null;
		}
	}

	@Override
	public List<VeiculoForm> findAllByOrderByNomeVeiculo() {
		List<Veiculo> listMarca = veiculoRepository.findAllByOrderByModeloAsc();
		return veiculoForm.convertDomainToDto(listMarca);
	}

	@Override
	public VeiculoForm findByIdVeiculo(Long id) {
		Optional<Veiculo> m1 = veiculoRepository.findById(id);
		if (m1.isPresent()) {
			return veiculoForm.convertDomainToType(m1.get());
		} else {
			return null;
		}
	}

	@Override
	public VeiculoForm removeVeiculo(Long id) {
		Optional<Veiculo> m1 = veiculoRepository.findById(id);
		if (m1.isPresent()) {
			Veiculo m2 = m1.get();
			veiculoRepository.delete(m2);
			return veiculoForm.convertDomainToType(m2);
		} else {
			return null;
		}
	}

	@Override
	public List<VeiculoForm> findAllVendido() {
		List<Veiculo> listMarcaVendido = veiculoRepository.findAllByIsVendido(false);
		return veiculoForm.convertDomainToDto(listMarcaVendido);
	}
}
