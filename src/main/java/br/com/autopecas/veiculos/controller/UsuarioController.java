package br.com.autopecas.veiculos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import br.com.autopecas.veiculos.enuns.ECargoTipo;
import br.com.autopecas.veiculos.model.Usuario;
import br.com.autopecas.veiculos.repository.UsuarioRepository;
import br.com.autopecas.veiculos.repository.UsuarioRepositoryImpl;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioRepositoryImpl usuarioRepoImpl;

	@GetMapping("/listar")
	public ResponseEntity<List<Usuario>> listarUsuarios() {

		List<Usuario> lista = new ArrayList<Usuario>();

		lista = usuarioRepository.findAll();

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
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
		try {

			Usuario _usuario = usuarioRepository.save(usuario);
			return new ResponseEntity<>(_usuario, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarPorId/{id}")
	public ResponseEntity<Usuario> consultarPorId(@PathVariable(value = "id") long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		try {
			if (usuario.isPresent()) {
				Usuario _usuario = usuario.get();
				return new ResponseEntity<>(_usuario, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {

		Optional<Usuario> usuarioData = usuarioRepository.findById(id);

		if (usuarioData.isPresent()) {
			Usuario _usuario = usuarioData.get();
			_usuario.setNome(usuario.getNome());
			_usuario.setCpf(usuario.getCpf());
			_usuario.setCargo(usuario.getCargo());
			return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") long id) {
		try {
			usuarioRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{cargo}")
	public ResponseEntity<List<Usuario>> getSpecificUsuarioByCargo(@PathVariable("cargo") String cargo) {

		List<Usuario> lista = new ArrayList<Usuario>();

		usuarioRepository.findBySpecificCargo(cargo).forEach(lista::add);

		return new ResponseEntity<>(lista, HttpStatus.OK);

	}

	@PostMapping("/find")
	public ResponseEntity<List<Usuario>> find(@RequestBody Usuario usuario) {
		try {

			List<Usuario> lista = new ArrayList<Usuario>();

//			if (usuario.getCpf() != null) {
//				Usuario _usuario = new Usuario();
//
//				_usuario = usuarioRepository.findByCpf(usuario.getCpf());
//				lista.add(_usuario);
//			}
//
//			if (usuario.getCargo() != null) {
//				List<Usuario> listaTemp = new ArrayList<Usuario>();
//
//				listaTemp = usuarioRepository.findBySpecificCargo(usuario.getCargo());
//				listaTemp.forEach(lista::add);
//			}
//
//			if (usuario.getNome() != null) {
//				List<Usuario> listaTemp2 = new ArrayList<Usuario>();
//
//				listaTemp2 = usuarioRepository.findBySpecificName(usuario.getNome());
//				listaTemp2.forEach(lista::add);
//			}
			
			lista = usuarioRepoImpl.findByFiltro(usuario);
			

			return new ResponseEntity<>(lista, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
