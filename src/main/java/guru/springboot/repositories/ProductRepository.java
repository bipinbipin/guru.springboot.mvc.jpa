package guru.springboot.repositories;

import guru.springboot.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Bipin on 9/29/2015.
 */
public interface ProductRepository extends CrudRepository<Product, Integer>{
}
