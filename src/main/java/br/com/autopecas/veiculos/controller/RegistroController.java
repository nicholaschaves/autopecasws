package br.com.autopecas.veiculos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.autopecas.veiculos.model.Registro;
import br.com.autopecas.veiculos.model.Usuario;
import br.com.autopecas.veiculos.repository.RegistroRepository;

@RestController
@RequestMapping(value = "/registro")
public class RegistroController {

	@Autowired
	RegistroRepository registroRepository;

	@GetMapping("/listar")
	public ResponseEntity<List<Registro>> listarRegistros() {

		List<Registro> lista = new ArrayList<Registro>();

		lista = registroRepository.findAll();

		try {
			if (lista.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(lista, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/novo")
	public ResponseEntity<Registro> criarUsuario(@RequestBody Registro registro) {
		try {
			
			registro.setDtInclusaoRegistro(new Date());

			Registro _registro = registroRepository.save(registro);
			return new ResponseEntity<>(_registro, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarPorId/{id}")
	public ResponseEntity<Registro> consultarPorId(@PathVariable(value = "id") long id) {

		Registro registro = registroRepository.getOne(id);

		try {
			if (registro != null) {
				Registro _registro = registro;
				return new ResponseEntity<>(_registro, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Registro> updateUsuario(@PathVariable("id") long id, @RequestBody Registro registro) {

		Optional<Registro> usuarioData = registroRepository.findById(id);

		if (usuarioData.isPresent()) {
			Registro _registro = usuarioData.get();
			_registro.setDescricaoRegistro(registro.getDescricaoRegistro());
			_registro.setPrecoRegistro(registro.getPrecoRegistro());
			_registro.setModeloVeiculo(registro.getModeloVeiculo());
			return new ResponseEntity<>(registroRepository.save(_registro), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") long id) {
		try {
			registroRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("/find")
	public ResponseEntity<List<Registro>> findByFiltro(@RequestBody Registro registro) {
		try {

			List<Registro> lista = new ArrayList<Registro>();

			lista = registroRepository.findByFiltro(registro);

			if (lista.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(lista, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
