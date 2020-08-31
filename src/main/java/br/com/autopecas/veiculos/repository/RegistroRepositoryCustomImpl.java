package br.com.autopecas.veiculos.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.autopecas.veiculos.model.Registro;

public class RegistroRepositoryCustomImpl implements RegistroRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Registro> findByFiltro(Registro registro) {

		StringBuilder sb = null;
		sb = new StringBuilder();

		sb.append("FROM Registro o");

		if (registro.getDescricaoRegistro() != null) {
			sb.append(" WHERE o.descricaoRegistro LIKE '%");
			sb.append(registro.getDescricaoRegistro());
			sb.append("%'");
		}

		sb.append(" ORDER BY id ASC");

		return entityManager.createQuery(sb.toString(), Registro.class).getResultList();

	}

}
