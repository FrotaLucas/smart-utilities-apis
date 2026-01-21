package project.demo.service.Customer;

import java.util.List;

import project.demo.model.Customer;

public interface CustomerService {
    
    Customer createCustomer (Customer customer);
 
    Customer getCustomerById (Long id);
  
    List<Customer> getAllCustomers();

    void deleleteCustomerById (Long id);

    Customer updateCustomer (Long id, Customer customer);
  
}
