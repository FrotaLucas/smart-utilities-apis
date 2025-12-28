package project.demo.repository.CustomerRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    
}