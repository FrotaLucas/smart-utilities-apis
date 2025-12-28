package project.demo.service.Customer;

import project.demo.model.Customer;
import project.demo.repository.CustomerRepository.CustomerRepository;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer addNewCustomer (Customer customer) {
        return customerRepository.save(customer);
    }   

}
