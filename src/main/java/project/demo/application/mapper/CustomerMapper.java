package project.demo.application.mapper;

import project.demo.application.dto.CustomerDto;
import project.demo.domain.entities.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerDto dto) {
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setId(dto.id());
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setGender(dto.gender());
        customer.setBirthDate(dto.birthDate());

        return customer;
    }

    public static CustomerDto toDto(Customer customer) {
        if (customer == null) return null;

        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getGender(),
                customer.getBirthDate()
        );
    }
}
