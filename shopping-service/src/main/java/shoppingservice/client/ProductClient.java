package shoppingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import shoppingservice.model.Customer;
import shoppingservice.model.Product;

@FeignClient(name = "product-service")
public interface ProductClient {

	@CircuitBreaker(name = "productCB", fallbackMethod = "fallBackgetProduct")
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

	@CircuitBreaker(name = "productCB", fallbackMethod = "fallBackupdateStockProduct")
    @GetMapping(value = "/products/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) Double quantity);

	public static  ResponseEntity<Product> fallBackgetProduct(@PathVariable("id") Long id, RuntimeException e){

		return new ProductFallbackFactory().getProduct(id);
	}
	
	public static  ResponseEntity<Product> fallBackupdateStockProduct(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) Double quantity, RuntimeException e){

		return new ProductFallbackFactory().getProduct(id);
	}
	
    }