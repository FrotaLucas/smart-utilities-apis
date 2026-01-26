package project.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import project.demo.application.Interfaces.CustomerApplicationService;
import project.demo.controller.dto.CustomerDto;
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

        Customer customer = new Customer();
        customer.setId(customerDto.id());
        customer.setFirstName(customerDto.firstName());
        customer.setLastName(customerDto.lastName());
        customer.setGender(customerDto.gender());
        customer.setBirthDate(customerDto.birthDate());

        Customer createdCustomer = customerService.createCustomer(customer);

        CustomerDto responseDto = new CustomerDto(
                createdCustomer.getId(),
                createdCustomer.getFirstName(),
                createdCustomer.getLastName(),
                createdCustomer.getGender(),
                createdCustomer.getBirthDate()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {

        Customer customer = customerService.getCustomerById(id);

        CustomerDto dto = new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getGender(),
                customer.getBirthDate()
        );

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {

        List<CustomerDto> customerDtos = customerService.getAllCustomers()
                .stream()
                .map(customer -> new CustomerDto(
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getGender(),
                        customer.getBirthDate()
                ))
                .toList();

        return ResponseEntity.ok(customerDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDto customerDto
    ) {

        if (id == null || customerDto == null) {
            return ResponseEntity.badRequest().build();
        }

        Customer customer = new Customer();
        customer.setId(customerDto.id());
        customer.setFirstName(customerDto.firstName());
        customer.setLastName(customerDto.lastName());
        customer.setGender(customerDto.gender());
        customer.setBirthDate(customerDto.birthDate());

        Customer updatedCustomer = customerService.updateCustomer(id, customer);

        CustomerDto responseDto = new CustomerDto(
                updatedCustomer.getId(),
                updatedCustomer.getFirstName(),
                updatedCustomer.getLastName(),
                updatedCustomer.getGender(),
                updatedCustomer.getBirthDate()
        );

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
