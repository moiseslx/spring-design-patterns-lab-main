package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Client;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter múltiplas implementações dessa mesma
 * interface.
 * 
 * @author Moises
 */
public interface ClientService {

    Iterable<Client> findAll();

    Client findById(Long id);

    void insert(Client client);

    void update(Long id, Client client);

    void delete(Long id);

}
