package project.demo.application;

import project.demo.application.dto.CustomerDto;
import project.demo.application.interfaces.CustomerApplicationService;
import project.demo.application.mapper.CustomerMapper;
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
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toEntity(customerDto);

        Customer createdCustomer = this.customerService.createCustomer(customer);

        return CustomerMapper.toDto(createdCustomer);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer =  customerService.getCustomerById(id);

        return CustomerMapper.toDto(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDto> getAllCustomers() {
         return customerService.getAllCustomers()
            .stream()
            .map(customer -> CustomerMapper.toDto(customer))
            .toList();
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
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {

        Customer customer = CustomerMapper.toEntity(customerDto);

        Customer updatedCustomer = customerService.updateCustomer(id, customer);

            return CustomerMapper.toDto(updatedCustomer);
    }
}