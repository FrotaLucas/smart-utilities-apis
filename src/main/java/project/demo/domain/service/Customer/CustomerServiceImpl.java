package project.demo.domain.service.Customer;

import java.util.List;

import org.springframework.stereotype.Service;

import project.demo.domain.entities.Customer;
import project.demo.infrastructure.repository.customer.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    // Return type to be a DTO type. See: https://github.com/rishiraj88/order-manager/blob/main/V1java/product/src/main/java/om/product/service/ProductServiceImpl.java
    public Customer createCustomer(Customer customer /* User DTO as parameter here and convert that into Entity type in this method by calling a util/helper.*/) {

        // toDo: handle validation
        // customer null check
        // mandatory fields check

        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }

    @Override
    public Customer getCustomerById(Long id) {
        // toDo: handle not found exception
        // Id == null check

        Customer dbCustomer = customerRepository.findById(id).orElse(null);

        return dbCustomer;
    }

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> dbCustomers = customerRepository.findAll();

        return dbCustomers;
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        Customer dbCustomer = getCustomerById(id);

        if (dbCustomer == null) {
            // toDo:
            // create Class ResourceNotFoundException extends RuntimeException ???
            // throw new ResourceNotFoundException("Customer not found with id: " + id);
            return false;
        }

        customerRepository.deleteById(id);

        return true;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer dbCustomer = getCustomerById(id);

        // id == null or customer == null check

        if (dbCustomer == null) {
            // toDo:
            // handle exception
        }

        dbCustomer.setFirstName(customer.getFirstName());
        dbCustomer.setLastName(customer.getLastName());
        dbCustomer.setBirthDate(customer.getBirthDate());
        dbCustomer.setGender(customer.getGender());

        Customer updatedCustomer = customerRepository.save(dbCustomer);

        return updatedCustomer;
    }
}
