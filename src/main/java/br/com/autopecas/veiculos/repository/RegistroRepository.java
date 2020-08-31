package br.com.autopecas.veiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autopecas.veiculos.model.Registro;
import br.com.autopecas.veiculos.model.Usuario;

public interface RegistroRepository extends JpaRepository<Registro, Long>, RegistroRepositoryCustom {

}
