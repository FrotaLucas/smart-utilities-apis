package project.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.demo.application.interfaces.ReadingApplicationService;
import project.demo.domain.entities.Customer;
import project.demo.domain.entities.Reading;
import project.demo.domain.service.Customer.CustomerService;
import project.demo.domain.service.Reading.ReadingService;

@Service
public class ReadingApplicationServiceImpl implements ReadingApplicationService {

    private final ReadingService readingService;

    private final CustomerService customerService;

    public ReadingApplicationServiceImpl(ReadingService readingService, CustomerService customerService) {
        this.readingService = readingService;
        this.customerService = customerService;
    }

    @Transactional
    @Override
    public Reading createReading(Reading reading) {

        Customer customer = reading.getCustomer();

        if (reading.getCustomer() == null || reading.getCustomer().getId() == null) {
            customer = customerService.createCustomer(reading.getCustomer());
        }

        else {
            Customer dbCustomer = customerService.getCustomerById(reading.getCustomer().getId());
            if (dbCustomer == null) {
                customer = customerService.createCustomer(reading.getCustomer());
            } else {
                customer = dbCustomer;
            }
        }

        reading.setCustomer(customer);
        return readingService.createReading(reading);
    }

    @Transactional(readOnly = true)
    @Override
    public Reading getReadingById(Long id) {
        Reading dbReading = readingService.getReadingById(id);

        // toDo
        // handle not found exception

        return dbReading;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reading> getAllReadings() {

        List<Reading> allReadings = readingService.getAllReadings();
        // toDo
        // handle empty list exception

        return allReadings;
    }

    @Transactional
    @Override
    public boolean deleleteReadingById(Long id) {

        // check if reading exists
        return readingService.deleleteReadingById(id);
    }

    @Transactional
    @Override
    public Reading updateReading(Long id, Reading reading) {
        Reading updatedReading = readingService.updateReading(id, reading);

        return updatedReading;

    }

}
