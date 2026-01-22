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

        Reading dbReading = readingRepository.findById(reading.getId()).orElse(null);

        //toDo: handle not found exception

        return dbReading;
    }


    @Override
    public List<Reading> getAllReadings() {
     
        List<Reading> dbReadings = readingRepository.findAll();

        //if (dbReadings.isEmpty())
        //handle not found exception

        return dbReadings;
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
