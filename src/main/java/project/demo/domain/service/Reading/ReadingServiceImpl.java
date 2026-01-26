package project.demo.domain.service.Reading;

import java.util.List;

import org.springframework.stereotype.Service;

import project.demo.domain.entities.Reading;
import project.demo.infrastructure.repository.reading.ReadingRepository;

//Service eh um bean spring gerenciado pelo Spring IoC container
@Service
public class ReadingServiceImpl implements ReadingService {

    private final ReadingRepository readingRepository;

    public ReadingServiceImpl(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    @Override
    public Reading createReading(Reading reading) {
        Reading createdReading = readingRepository.save(reading);

        return createdReading;
    }

    @Override
    public Reading getReadingById(Long id) {

        Reading dbReading = readingRepository.findById(id).orElse(null);

        // toDo: handle not found exception

        return dbReading;
    }

    @Override
    public List<Reading> getAllReadings() {

        List<Reading> dbReadings = readingRepository.findAll();

        // if (dbReadings.isEmpty())
        // handle not found exception

        return dbReadings;
    }

    @Override
    public boolean deleleteReadingById(Long id) {

        Reading dbReading = getReadingById(id);

        // toDo: handle not found exception
        if ( dbReading == null)
        {
            return false;
        }

        readingRepository.deleteById(id);
        return true;
    }

    @Override
    public Reading updateReading(Long id, Reading reading) {

        Reading dbReading = getReadingById(id);

        // toDo: handle not found exception
        // if ( dbReading == null)

        dbReading.setKindOfMeter(reading.getKindOfMeter());
        dbReading.setComment(reading.getComment());
        dbReading.setMeterId(reading.getMeterId());
        dbReading.setMeterCount(reading.getMeterCount());
        dbReading.setSubstitute(reading.getSubstitute());
        dbReading.setDateOfReading(reading.getDateOfReading());

        readingRepository.save(dbReading);

        return dbReading;
    }

    @Override
    public List<Reading> getReadingByCustomerId(Long customerId) {

       List<Reading> readings = readingRepository.findByCustomerId(customerId);
       return readings;
    }

}
