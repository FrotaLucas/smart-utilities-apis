package project.demo.application;

import project.demo.application.Interfaces.CustomerApplicationService;
import project.demo.model.Customer;
import project.demo.model.Reading;
import project.demo.service.Customer.CustomerService;
import project.demo.service.Reading.ReadingService;

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
    public void deleleteCustomerById(Long id) {
        List<Reading> readings = readingService.getReadingByCustomerId(id);
        
        for (Reading reading : readings) {
            reading.setCustomer(null);
        }

        customerService.deleleteCustomerById(id);
    }

    @Transactional
    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
}