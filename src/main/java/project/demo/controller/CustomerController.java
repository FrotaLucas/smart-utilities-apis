package project.demo.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.demo.model.Customer;
import project.demo.service.Customer.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)
    {
        Customer createdCustomer = customerService.createCustomer(customer);
        
        //toDo
        //handle exceptions and validation

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(Long id)
    {
        Customer dbCustomer = customerService.getCustomerById(id);

        //toDo
        //handle exceptions and validation

        return ResponseEntity.ok(dbCustomer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers()
    {
        List<Customer> dbCustomers = customerService.getAllCustomers();

        return ResponseEntity.ok(dbCustomers);
    }
    
}
