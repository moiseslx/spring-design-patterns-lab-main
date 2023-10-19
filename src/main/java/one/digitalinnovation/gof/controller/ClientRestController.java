package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import one.digitalinnovation.gof.model.Client;
import one.digitalinnovation.gof.service.ClientService;

/**
 * Este {@link RestController} representa nossa <b>Facade</b>, pois abstract
 * toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 * @author Moises
 */
@RestController
@RequestMapping("clients")
public class ClientRestController {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<Iterable<Client>> findAll() {
		return ResponseEntity.ok(clientService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		return ResponseEntity.ok(clientService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Client> insert(@RequestBody Client client) {
		clientService.insert(client);
		return ResponseEntity.ok(client);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
		clientService.update(id, client);
		return ResponseEntity.ok(client);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clientService.delete(id);
		return ResponseEntity.ok().build();
	}
}
