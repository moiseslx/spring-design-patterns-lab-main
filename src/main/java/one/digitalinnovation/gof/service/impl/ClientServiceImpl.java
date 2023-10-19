package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Address;
import one.digitalinnovation.gof.model.AddressRepository;
import one.digitalinnovation.gof.model.Client;
import one.digitalinnovation.gof.model.ClientRepository;
import one.digitalinnovation.gof.service.ClientService;
import one.digitalinnovation.gof.service.ViaCepService;

/**
 * Implementação da Strategy {@link ClientService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Como um {@link Service}, esta classe
 * é tratada como um Singleton.
 * 
 * @author Moises
 */
@Service
public class ClientServiceImpl implements ClientService {

	// Singleton: Injete os componentes do Spring com @Autowired.
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ViaCepService viaCepService;

	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, fornecendo uma interface simples.

	/**
	 * Recupera todos os clientes do repositório.
	 *
	 * @return Iterável de objetos Client representando todos os clientes.
	 */
	@Override
	public Iterable<Client> findAll() {
		return clientRepository.findAll();
	}

	/**
	 * Recupera um Cliente pelo seu ID.
	 * 
	 * @param id O ID do Cliente a ser recuperado.
	 * @return O objeto Client com o ID fornecido.
	 */
	@Override
	public Client findById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.orElse(null); // Retorna o cliente, ou null se não existir.
	}

	/**
	 * Insere um cliente no sistema.
	 *
	 * @param client O cliente a ser inserido.
	 */
	@Override
	public void insert(Client client) {
		// Salve o cliente com o código postal associado
		saveClientWithAddress(client);
	}

	/**
	 * Atualiza um cliente com o ID fornecido.
	 * Se o cliente existir, salva o cliente com as informações atualizadas.
	 *
	 * @param id O ID do cliente a ser atualizado.
	 * @param client As informações atualizadas do cliente.
	 */
	@Override
	public void update(Long id, Client client) {
		// Verifique se o cliente existe pelo ID
		Optional<Client> clientBd = clientRepository.findById(id);

		// Se o cliente existir, salve o cliente com as informações atualizadas
		if (clientBd.isPresent()) {
			saveClientWithAddress(client);
		}
	}

	/**
	 * Exclui um cliente pelo ID.
	 * 
	 * @param id O ID do cliente a ser excluído.
	 */
	@Override
	public void delete(Long id) {
		// Exclua o cliente pelo ID
		clientRepository.deleteById(id);
	}

	/**
	 * Salva um cliente com seu endereço.
	 *
	 * @param client O cliente a ser salvo.
	 */
	private void saveClientWithAddress(Client client) {
		// Verifique se o endereço do cliente já existe (pelo CEP).
		String cep = client.getAddress().getCep();
		Address address = addressRepository.findById(cep).orElseGet(() -> {
			// Se não existir, integre com o ViaCEP e persista o endereço retornado.
			Address newAddress = viaCepService.consultCep(cep);
			addressRepository.save(newAddress);
			return newAddress;
		});
		client.setAddress(address);
		// Insira o cliente, vinculando ao endereço (novo ou existente).
		clientRepository.save(client);
	}
}
