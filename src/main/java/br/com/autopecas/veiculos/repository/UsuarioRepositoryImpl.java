package br.com.autopecas.veiculos.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.autopecas.veiculos.model.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Usuario> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends Usuario> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Usuario> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Usuario> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Usuario> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Usuario> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Usuario entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Usuario> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends Usuario> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Usuario> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Usuario> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Usuario> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Usuario> findBySpecificName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findBySpecificCargo(String cargo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findByFiltro(Usuario usuario) {
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

		return entityManager.createQuery(sb.toString(), Usuario.class).getResultList();

	}

}
