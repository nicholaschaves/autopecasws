package br.com.autopecas.veiculos.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.autopecas.veiculos.model.Usuario;

@Repository
public interface UsuarioRepositoryCustom {

	public List<Usuario> findUsersByFiltro(Usuario usuario);
	
}
