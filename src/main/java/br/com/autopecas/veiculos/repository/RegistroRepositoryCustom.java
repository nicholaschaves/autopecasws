package br.com.autopecas.veiculos.repository;

import java.util.List;

import br.com.autopecas.veiculos.model.Registro;

public interface RegistroRepositoryCustom {

	public List<Registro> findByFiltro(Registro registro);
	
}
