package project.demo.application.Interfaces;

import java.util.List;

import project.demo.model.Reading;

public interface ReadingApplicationService {
    
    Reading createReading(Reading reading);

    Reading getReadingById (Long id);
 
    List<Reading> getAllReadings();

    void deleleteReadingById (Long id);

    Reading updateReading (Long id, Reading reading);
}
