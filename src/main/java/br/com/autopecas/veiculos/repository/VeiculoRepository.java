package br.com.autopecas.veiculos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.autopecas.veiculos.model.Veiculo;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
	
	 List<Veiculo> findByPlaca(String placa);
	 List<Veiculo> findByModelo(String modelo);
	 List<Veiculo> findByMarca(String marca);
	
}
