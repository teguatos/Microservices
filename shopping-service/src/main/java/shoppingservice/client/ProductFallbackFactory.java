package shoppingservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import shoppingservice.model.Category;
import shoppingservice.model.Customer;
import shoppingservice.model.Product;

@Component
public class ProductFallbackFactory{
    public ResponseEntity<Product> getProduct(Long id) {
        Product customer = Product.builder()
                .name("none")
                .description("none")
                .stock(0.0)
                .price(0.0)
                .status("none")
                .category(Category.builder().name("none").build())
                .build();
        return ResponseEntity.ok(customer);
    }

}