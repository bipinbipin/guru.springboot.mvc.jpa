package guru.springboot.services;

import guru.springboot.domain.Customer;

/**
 * Created by Bipin on 6/14/2016.
 */
public interface CustomerService {

    Iterable<Customer> listAllCustomer();

    Customer getCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
