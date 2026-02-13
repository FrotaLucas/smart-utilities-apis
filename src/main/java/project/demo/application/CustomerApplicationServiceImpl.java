package project.demo.application;

import project.demo.application.interfaces.CustomerApplicationService;
import project.demo.domain.entities.Customer;
import project.demo.domain.entities.Reading;
import project.demo.domain.service.Customer.CustomerService;
import project.demo.domain.service.Reading.ReadingService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private final CustomerService customerService;
    private final ReadingService readingService;

    public CustomerApplicationServiceImpl(CustomerService customerService, ReadingService readingService)
    {
        this.customerService = customerService;
        this.readingService = readingService;
    }

    @Transactional
    @Override
    public Customer createCustomer(Customer customer) {
        return customerService.createCustomer(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCustomerById(Long id) {
        return customerService.getCustomerById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Transactional
    @Override
    public boolean deleteCustomerById(Long id) {
        List<Reading> readings = readingService.getReadingByCustomerId(id);
        

        for (Reading reading : readings) {
            reading.setCustomer(null);
        }

        return customerService.deleteCustomerById(id);
    }

    @Transactional
    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
}