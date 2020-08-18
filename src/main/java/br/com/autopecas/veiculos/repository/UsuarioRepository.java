package br.com.autopecas.veiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autopecas.veiculos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
