package br.com.autopecas.veiculos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import br.com.autopecas.veiculos.enuns.ECargoTipo;
import br.com.autopecas.veiculos.model.Usuario;
import br.com.autopecas.veiculos.repository.UsuarioRepositoryCustom;
import br.com.autopecas.veiculos.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

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

			if (usuario.getUsername() != null && usuario.getCpf() != null) {
				Usuario usuarioExistente = new Usuario();

				usuarioExistente = usuarioRepository.getUserByUsernameAndCpf(usuario.getUsername().trim().toLowerCase(), usuario.getCpf().trim());

				if (usuarioExistente == null) {
					usuario.setSenha(DigestUtils.sha256Hex(usuario.getSenha()));

					Usuario _usuario = usuarioRepository.save(usuario);
					return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarPorId/{id}")
	public ResponseEntity<Usuario> consultarPorId(@PathVariable(value = "id") long id) {

		Usuario usuario = usuarioRepository.getOne(id);

		try {
			if (usuario != null) {
				Usuario _usuario = usuario;
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

	@PostMapping("/autenticar")
	public ResponseEntity<Usuario> autenticar(@RequestBody Usuario usuario) {

		if (usuario != null) {
			if (usuario.getUsername() != null && usuario.getSenha() != null) {

				try {

					Usuario usuarioAutenticado = new Usuario();

					usuario.setSenha(DigestUtils.sha256Hex(usuario.getSenha()));

					usuarioAutenticado = usuarioRepository.autenticarUsuario(usuario.getUsername(), usuario.getSenha());

					if (usuarioAutenticado != null) {
						return new ResponseEntity<>(usuarioAutenticado, HttpStatus.OK);
					} else {
						usuario.setUsername(null);
						usuario.setSenha(null);
						return new ResponseEntity<>(usuario, HttpStatus.BAD_REQUEST);
					}

				} catch (Exception e) {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/resetSenha")
	public ResponseEntity<Usuario> resetSenha(@RequestBody Usuario usuario) {

		if (usuario != null) {
			if (usuario.getUsername() != null && usuario.getCpf() != null) {

				try {

					Usuario usuarioTemp = new Usuario();

					usuarioTemp = usuarioRepository.getUserByUsernameAndCpf(usuario.getUsername().trim().toLowerCase(), usuario.getCpf().trim());

					if (usuarioTemp != null) {

						usuarioTemp.setSenha(DigestUtils.sha256Hex("123456"));
//							_usuario.setSenha("123456");
						return new ResponseEntity<>(usuarioRepository.save(usuarioTemp), HttpStatus.OK);

					} else {
						usuario = null;
						return new ResponseEntity<>(usuario, HttpStatus.BAD_REQUEST);
					}

				} catch (Exception e) {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/findByFiltro")
	public ResponseEntity<List<Usuario>> findUsersByFiltro(@RequestBody Usuario usuario) {

		List<Usuario> lista = new ArrayList<Usuario>();

		lista = usuarioRepository.findUsersByFiltro(usuario);

		if (lista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}

	}

}
