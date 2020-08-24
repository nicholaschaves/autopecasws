package br.com.autopecas.veiculos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.autopecas.veiculos.model.Veiculo;
import br.com.autopecas.veiculos.repository.VeiculoRepository;

@RestController
@RequestMapping(value = "/veiculo")
public class VeiculoController {

	@Autowired
	VeiculoRepository veiculorepository;

	@GetMapping("/listar")
	public ResponseEntity<List<Veiculo>> findAllVeiculos(@RequestParam(required = false) String modelo) {

		try {

			List<Veiculo> veiculos = new ArrayList<Veiculo>();
			
			veiculorepository.findAll().forEach(veiculos::add);
			
			if(veiculos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			
			return new ResponseEntity<>(veiculos, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/novo")
	public ResponseEntity<Veiculo> createVeiculo(@RequestBody Veiculo veiculo){
		try {
			
			Veiculo _veiculo = veiculorepository.save(veiculo);
			return new ResponseEntity<>(_veiculo, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/alterar/{id}")
	public ResponseEntity<Veiculo> updateVeiculo(@PathVariable("id") String id, @RequestBody Veiculo veiculo){
		
		Optional<Veiculo> veiculoData = veiculorepository.findById(id);
		
		if(veiculoData.isPresent()) {
			Veiculo _veiculo = veiculoData.get();
			_veiculo.setPlaca(veiculo.getPlaca());
			_veiculo.setModelo(veiculo.getModelo());
			_veiculo.setAnoFabricacao(veiculo.getAnoFabricacao());
			_veiculo.setAnoModelo(veiculo.getAnoModelo());
			_veiculo.setMarca(veiculo.getMarca());
			return new ResponseEntity<>(veiculorepository.save(_veiculo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<HttpStatus> deleteVeiculo(@PathVariable("id") String id) {
	  try {
		  veiculorepository.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@PostMapping("/listarPorCampo")
	public ResponseEntity<List<Veiculo>> findVeiculoByPlaca(@RequestBody Veiculo veiculo){
		try {
			
			List<Veiculo> veiculos = new ArrayList<Veiculo>();
			List<Veiculo> veiculosFormatted = new ArrayList<Veiculo>();
			
			if(veiculo.getPlaca() == null && veiculo.getModelo() == null && veiculo.getMarca() == null) {
				veiculos = veiculorepository.findAll();
			}
			
			if(veiculo.getPlaca() != null) {
				veiculorepository.findByPlaca(veiculo.getPlaca()).forEach(veiculos::add);
			}
			if(veiculo.getModelo() != null) {
				veiculorepository.findByModelo(veiculo.getModelo()).forEach(veiculos::add);
			}
			if(veiculo.getMarca() != null) {
				veiculorepository.findByMarca(veiculo.getMarca()).forEach(veiculos::add);
			}
			
			
			
			if(veiculos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				veiculosFormatted = veiculos.stream().distinct().collect(Collectors.toList());
			}
			
			return new ResponseEntity<>(veiculosFormatted, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
