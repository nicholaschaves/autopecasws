package br.com.autopecas.veiculos.repository;

import java.util.List;

import br.com.autopecas.veiculos.model.Veiculo;

public interface VeiculoRepositoryCustom {
	
	List<Veiculo> findByFiltro(Veiculo veiculo);

}
