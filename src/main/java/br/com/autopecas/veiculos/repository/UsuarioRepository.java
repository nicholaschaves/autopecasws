package br.com.autopecas.veiculos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.autopecas.veiculos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryCustom {

	@Query("FROM Usuario WHERE nome LIKE %?1% ORDER BY nome ASC")
	public List<Usuario> findBySpecificName(String nome);

	@Query("FROM Usuario WHERE cargo = ?1 ORDER BY nome ASC")
	public List<Usuario> findBySpecificCargo(String cargo);

	@Query("FROM Usuario WHERE cpf = ?1")
	public Usuario findByCpf(String cpf);

	@Query("FROM Usuario WHERE username = ?1 AND senha = ?2")
	public Usuario autenticarUsuario(String username, String senha);

	@Query("FROM Usuario WHERE username = ?1 AND cpf = ?2")
	public Usuario getUserByUsernameAndCpf(String username, String cpf);

}
