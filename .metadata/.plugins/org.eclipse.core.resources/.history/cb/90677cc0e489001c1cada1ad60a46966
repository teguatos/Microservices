package shoppingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import shoppingservice.model.Customer;

@FeignClient(name = "customer-service")
public interface CustomerClient {

	
	@CircuitBreaker(name = "customerCB", fallbackMethod = "fallBackGetCustomer")
	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
	
	public static ResponseEntity<Customer> fallBackGetCustomer(@PathVariable("id") long id, RuntimeException e){

		CustomerFallbackFactory customerFallbackFactory = new CustomerFallbackFactory();
		return customerFallbackFactory.getCustomer(id);
	}
}
