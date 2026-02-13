package project.demo.application.interfaces;

import java.util.List;

import project.demo.application.dto.ReadingDto;

public interface ReadingApplicationService {
    
    ReadingDto createReading(ReadingDto reading);

    ReadingDto getReadingById (Long id);
 
    List<ReadingDto> getAllReadings();

    boolean deleleteReadingById (Long id);

    ReadingDto updateReading (Long id, ReadingDto reading);
}
