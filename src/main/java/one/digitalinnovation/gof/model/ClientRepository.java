package one.digitalinnovation.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Moises
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}