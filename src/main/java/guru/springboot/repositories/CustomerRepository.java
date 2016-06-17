package guru.springboot.repositories;

import guru.springboot.domain.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Bipin on 6/14/2016.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
