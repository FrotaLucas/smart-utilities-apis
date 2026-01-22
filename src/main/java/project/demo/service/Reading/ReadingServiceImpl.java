package project.demo.service.Reading;

import java.util.List;

import project.demo.model.Reading;
import project.demo.repository.ReadingRepository.ReadingRepository;

public class ReadingServiceImpl implements ReadingService{
    
    private final ReadingRepository readingRepository;

    public ReadingServiceImpl(ReadingRepository readingRepository)
    {
        this.readingRepository = readingRepository;
    }


    public Reading createReading(Reading reading)
    {
        Reading createdReading = readingRepository.save(reading);

        return createdReading;
    }


    @Override
    public Reading getReadingById(Reading reading) {
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
