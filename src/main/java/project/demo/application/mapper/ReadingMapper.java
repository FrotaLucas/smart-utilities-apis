package project.demo.application.mapper;

import project.demo.application.dto.ReadingDto;
import project.demo.domain.entities.Reading;

public class ReadingMapper {

    public static ReadingDto toDto(Reading reading) {

        if (reading == null) {
            return null;
        }

        return new ReadingDto(
                reading.getId(),
                reading.getKindOfMeter(),
                reading.getComment(),
                reading.getMeterId(),
                reading.getMeterCount(),
                reading.getSubstitute(),
                reading.getDateOfReading()
        );
    }

    public static Reading toEntity(ReadingDto readingDto) {

        if (readingDto == null) {
            return null;
        }

        Reading reading = new Reading();

        reading.setId(readingDto.id());
        reading.setKindOfMeter(readingDto.kindOfMeter());
        reading.setComment(readingDto.comment());
        reading.setMeterId(readingDto.meterId());
        reading.setMeterCount(readingDto.meterCount());
        reading.setSubstitute(readingDto.substitute());
        reading.setDateOfReading(readingDto.dateOfReading());

        return reading;
    }
}
