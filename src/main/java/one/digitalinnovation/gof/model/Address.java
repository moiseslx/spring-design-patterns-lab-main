package one.digitalinnovation.gof.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Os atributos deste modelo foram gerados automaticamente pelo site
 * jsonschema2pojo.org. Usamos a resposta JSON da API ViaCEP para esse propósito.
 * 
 * @see <a href="https://www.jsonschema2pojo.org">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * 
 * Autor: Moises
 */
@Entity
@Data
public class Address {

    @Id
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
