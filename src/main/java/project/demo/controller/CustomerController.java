package project.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import project.demo.application.dto.CustomerDto;
import project.demo.application.interfaces.CustomerApplicationService;
import project.demo.domain.entities.Customer;


@Tag(name = "Customers", description = "Endpoints of Customers")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerApplicationService customerService;

    public CustomerController(CustomerApplicationService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {

        CustomerDto responseDto = customerService.createCustomer(customerDto);
    
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {

        CustomerDto responseDto = customerService.getCustomerById(id);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {

        List<CustomerDto> responseDto = customerService.getAllCustomers();

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDto customerDto
    ) {

        if (id == null || customerDto == null) {
            return ResponseEntity.badRequest().build();
        }


        CustomerDto responseDto = customerService.updateCustomer(id, customerDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        if (!customerService.deleteCustomerById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
