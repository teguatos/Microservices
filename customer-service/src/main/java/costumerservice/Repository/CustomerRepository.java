package costumerservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import costumerservice.Repository.entity.Customer;
import costumerservice.Repository.entity.Region;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {
    public Customer findByNumberID(String numberID);
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByRegion(Region region);
}