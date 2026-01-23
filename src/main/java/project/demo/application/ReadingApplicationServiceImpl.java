package project.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;

import project.demo.application.Interfaces.ReadingApplicationService;
import project.demo.model.Customer;
import project.demo.model.Reading;
import project.demo.service.Customer.CustomerService;
import project.demo.service.Reading.ReadingService;

@Service
public class ReadingApplicationServiceImpl implements ReadingApplicationService {

    private final ReadingService readingService;

    private final CustomerService customerService;

    public ReadingApplicationServiceImpl(ReadingService readingService, CustomerService customerService)
    {
        this.readingService = readingService;
        this.customerService = customerService;
    }

    @Override
    public Reading createReading(Reading reading) {

        Customer customer = new Customer();

        Customer dbCustomer = customerService.getCustomerById(reading.getCustomer().getId());

        if(dbCustomer == null)
        {
            customer = customerService.createCustomer(reading.getCustomer());
        }
        else
        {
            customer = dbCustomer;
        }

        reading.setCustomer(customer);
    
        return readingService.createReading(reading);
    }

    @Override
    public Reading getReadingById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReadingById'");
    }

    @Override
    public List<Reading> getAllReadings() {
        
        List<Reading> allReadings = readingService.getAllReadings();
        // toDo
        //handle empty list exception

        return allReadings;
    }

    @Override
    public void deleleteReadingById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleleteReadingById'");
    }

    @Override
    public Reading updateReading(Long id, Reading reading) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReading'");
    }
    
}
