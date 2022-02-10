package store.serviceproduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import store.serviceproduct.entity.Category;
import store.serviceproduct.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByCategory(Category category);

}
