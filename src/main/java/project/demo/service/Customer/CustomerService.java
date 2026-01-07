package project.demo.service.Customer;

import org.springframework.stereotype.Service;

import project.demo.model.Customer;
import project.demo.repository.CustomerRepository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer addNewCustomer (Customer customer) {
        return customerRepository.save(customer);
    }   

}
