package project.demo.application.interfaces;

import java.util.List;

import project.demo.domain.entities.Reading;

public interface ReadingApplicationService {
    
    Reading createReading(Reading reading);

    Reading getReadingById (Long id);
 
    List<Reading> getAllReadings();

    boolean deleleteReadingById (Long id);

    Reading updateReading (Long id, Reading reading);
}
