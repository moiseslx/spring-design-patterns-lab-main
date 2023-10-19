package one.digitalinnovation.gof.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.gof.model.Address;

/**
 * Cliente HTTP, criado via OpenFeign, para consumir a API ViaCEP.
 * 
 * @see https://spring.io/projects/spring-cloud-openfeign - Spring Cloud OpenFeign
 * @see https://viacep.com.br - ViaCEP
 * 
 * @author Moises
 */
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping("/{cep}/json/")
    Address consultCep(@PathVariable("cep") String cep);
}
