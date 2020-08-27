package br.com.autopecas.veiculos.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.autopecas.veiculos.model.Veiculo;

public class VeiculoRepositoryCustomImpl implements VeiculoRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Veiculo> findByFiltro(Veiculo veiculo) {

		Query query = new Query();

		if (veiculo.getPlaca() != null) {
			query.addCriteria(Criteria.where("placa").is(veiculo.getPlaca()));
		}

		if (veiculo.getModelo() != null) {
			query.addCriteria(Criteria.where("modelo").is(veiculo.getModelo()));
		}
		if (veiculo.getMarca() != null) {
			query.addCriteria(Criteria.where("marca").is(veiculo.getMarca()));
		}
		if (veiculo.getAnoModelo() != null) {
			query.addCriteria(Criteria.where("anoModelo").is(veiculo.getAnoModelo()));
		}
		if (veiculo.getAnoFabricacao() != null) {
			query.addCriteria(Criteria.where("anoFabricacao").is(veiculo.getAnoFabricacao()));
		}
		

//		Query query = new Query(Criteria.where("placa").is(veiculo.getPlaca()));

		return mongoTemplate.find(query, Veiculo.class);

	}

}
