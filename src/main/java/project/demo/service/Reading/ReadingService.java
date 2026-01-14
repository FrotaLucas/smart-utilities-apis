package project.demo.service.Reading;

import project.demo.model.Reading;
import project.demo.repository.ReadingRepository.ReadingRepository;

public class ReadingService {
    
    private final ReadingRepository readingRepository;

    public ReadingService(ReadingRepository readingRepository)
    {
        this.readingRepository = readingRepository;
    }


    public Reading createReading(Reading reading)
    {
        Reading createdReading = readingRepository.save(reading);

        return createdReading;
    }
}
