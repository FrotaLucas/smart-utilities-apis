package project.demo.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.demo.application.dto.ReadingDto;
import project.demo.application.interfaces.ReadingApplicationService;
import project.demo.application.mapper.ReadingMapper;
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
    public ReadingDto createReading(ReadingDto readingDto) {

        Reading reading = ReadingMapper.toEntity(readingDto);
        Customer customer = reading.getCustomer();

        if(reading.getCustomer() == null)
        {
             throw new IllegalArgumentException("Customer must be provided");
        }

        if (reading.getCustomer().getId() == null) {
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
        Reading createdReading = readingService.createReading(reading);


        return ReadingMapper.toDto(createdReading);
    }

    @Transactional(readOnly = true)
    @Override
    public ReadingDto getReadingById(Long id) {
        Reading dbReading = readingService.getReadingById(id);

        // toDo
        // handle not found exception

        return ReadingMapper.toDto(dbReading);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReadingDto> getAllReadings() {

        List<Reading> allReadings = readingService.getAllReadings();
        // toDo
        // handle empty list exception

        return allReadings
                .stream()
                .map(reading -> ReadingMapper.toDto(reading))
                .toList();

    }

    @Transactional
    @Override
    public boolean deleleteReadingById(Long id) {

        // check if reading exists
        return readingService.deleleteReadingById(id);
    }

    @Transactional
    @Override
    public ReadingDto updateReading(Long id, ReadingDto readingDto) {
        Reading reading = ReadingMapper.toEntity(readingDto);
        Reading updatedReading = readingService.updateReading(id, reading);

        return ReadingMapper.toDto(updatedReading);
    }

}
