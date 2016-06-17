package guru.springboot.services;

import guru.springboot.domain.Product;

/**
 * Created by Bipin on 9/30/2015.
 */
public interface ProductService {

    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}
