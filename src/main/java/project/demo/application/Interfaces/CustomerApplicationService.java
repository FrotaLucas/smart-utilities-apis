package project.demo.application.interfaces;

import java.util.List;

import project.demo.application.dto.CustomerDto;
import project.demo.domain.entities.Customer;

public interface CustomerApplicationService {

  CustomerDto createCustomer(CustomerDto customer);

  CustomerDto getCustomerById(Long id);

  List<CustomerDto> getAllCustomers();

  boolean deleteCustomerById(Long id);

  CustomerDto updateCustomer(Long id, CustomerDto customer);
}
