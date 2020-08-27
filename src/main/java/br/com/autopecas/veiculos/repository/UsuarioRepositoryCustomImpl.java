package br.com.autopecas.veiculos.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.autopecas.veiculos.model.Usuario;

public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Usuario> findUsersByFiltro(Usuario usuario){
		
		StringBuilder sb = null;
		sb = new StringBuilder();

		sb.append("FROM Usuario o");

		if (usuario.getNome() != null) {
			sb.append(" WHERE o.nome LIKE '%");
			sb.append(usuario.getNome());
			sb.append("%'");
		}

		if (usuario.getCargo() != null) {
			if (usuario.getNome() == null) {
				sb.append(" WHERE o.cargo LIKE '%");
				sb.append(usuario.getCargo());
				sb.append("%'");
			} else {
				sb.append(" AND o.cargo LIKE '%");
				sb.append(usuario.getCargo());
				sb.append("%'");
			}
		}

		if (usuario.getCpf() != null) {
			if (usuario.getNome() == null && usuario.getCargo() == null) {
				sb.append(" WHERE o.cpf LIKE '%");
				sb.append(usuario.getCpf());
				sb.append("%'");
			} else {
				sb.append(" AND o.cpf LIKE '%");
				sb.append(usuario.getCpf());
				sb.append("%'");
			}
		}
		
		sb.append(" ORDER BY id ASC");

		return entityManager.createQuery(sb.toString(), Usuario.class).getResultList();
		
		
	}
	
	
}
