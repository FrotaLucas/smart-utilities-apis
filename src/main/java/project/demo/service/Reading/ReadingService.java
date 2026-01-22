package project.demo.service.Reading;

import java.util.List;

import project.demo.model.Reading;

public interface ReadingService {
    
    Reading createReading(Reading reading);

    Reading getReadingById (Reading reading);
 
    List<Reading> getAllReadings();

    void deleleteReadingById (Long id);

    Reading updateReading (Long id, Reading reading);
}
