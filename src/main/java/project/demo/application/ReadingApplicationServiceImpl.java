package project.demo.application;

import java.util.List;

import project.demo.application.Interfaces.ReadingApplicationService;
import project.demo.model.Reading;
import project.demo.service.Customer.CustomerService;
import project.demo.service.Reading.ReadingService;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createReadingWithNewCustomer'");
    }

    @Override
    public Reading createReadingForExistingCustomer(Reading reading) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createReadingForExistingCustomer'");
    }

    @Override
    public Reading getReadingById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReadingById'");
    }

    @Override
    public List<Reading> getAllReadings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllReadings'");
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
