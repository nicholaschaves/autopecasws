package br.com.autopecas.veiculos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.autopecas.veiculos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("FROM Usuario WHERE nome LIKE '%?1%' ORDER BY nome ASC")
	public List<Usuario> findBySpecificName(String nome);
	
	@Query("FROM Usuario WHERE cargo = ?1 ORDER BY nome ASC")
	public List<Usuario> findBySpecificCargo(String cargo);
	
	@Query("FROM Usuario WHERE cpf = ?1")
	public Usuario findByCpf(String cpf);
	
	public List<Usuario> findByFiltro(Usuario usuario);

}
