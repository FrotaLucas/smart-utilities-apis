package project.demo.service.Reading;

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

    

    //implementations
}
