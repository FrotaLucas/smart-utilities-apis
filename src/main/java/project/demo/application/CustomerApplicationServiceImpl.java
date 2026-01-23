package project.demo.application;

import project.demo.application.Interfaces.CustomerApplicationService;
import project.demo.model.Customer;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    @Override
    public Customer createCustomer(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCustomer'");
    }

    @Override
    public Customer getCustomerById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerById'");
    }

    @Override
    public List<Customer> getAllCustomers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCustomers'");
    }

    @Override
    public void deleleteCustomerById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleleteCustomerById'");
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }
    
}
