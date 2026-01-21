package project.demo.service.Customer;

import java.util.List;

import org.springframework.stereotype.Service;

import project.demo.model.Customer;
import project.demo.repository.CustomerRepository.CustomerRepository;

@Service
public class CustomerServiceImpl {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }


    public Customer createCustomer (Customer customer)
    {

        //toDo: handle validation
        // customer null check
        // mandatory fields check

        Customer createdCustomer = customerRepository.save(customer);

        return createdCustomer;
    }  
    
    public Customer getCustomerById (Long id)
    {
        //toDo: handle not found exception
        //Id == null check

        Customer dbCustomer = customerRepository.findById(id).orElse(null);

        return dbCustomer;
    }

    public List<Customer> getAllCustomers()
    {

        List<Customer> dbCustomers = customerRepository.findAll();

        return dbCustomers;
    }

    public void deleleteCustomerById (Long id)
    {
        Customer dbCustomer = getCustomerById(id);

       if ( dbCustomer == null)
       {
            //toDo:
            //create Class ResourceNotFoundException extends RuntimeException ???
            //throw new ResourceNotFoundException("Customer not found with id: " + id);
       }

       customerRepository.deleteById(id);

    }

    public Customer updateCustomer (Long id, Customer customer)
    {
        Customer dbCustomer = getCustomerById(id);

        //id == null or customer == null check

        if ( dbCustomer == null)
        {
            //toDo:
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
