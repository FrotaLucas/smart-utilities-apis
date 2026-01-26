package project.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import project.demo.application.Interfaces.ReadingApplicationService;
import project.demo.controller.dto.ReadingDto;
import project.demo.domain.entities.Reading;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Tag(name = "Readings", description = "Endpoints of Readings")
@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    private final ReadingApplicationService readingService;

    public ReadingController(ReadingApplicationService readingService) {
        this.readingService = readingService;
    }

    @PostMapping
    public ResponseEntity<ReadingDto> createReading(@RequestBody ReadingDto readingDto) {

        Reading reading = new Reading();
        reading.setId(readingDto.id());
        reading.setKindOfMeter(readingDto.kindOfMeter());
        reading.setComment(readingDto.comment());
        reading.setMeterId(readingDto.meterId());
        reading.setMeterCount(readingDto.meterCount());
        reading.setSubstitute(readingDto.substitute());
        reading.setDateOfReading(readingDto.dateOfReading());

        Reading createdReading = readingService.createReading(reading);

        ReadingDto responseDto = new ReadingDto(
                createdReading.getId(),
                createdReading.getKindOfMeter(),
                createdReading.getComment(),
                createdReading.getMeterId(),
                createdReading.getMeterCount(),
                createdReading.getSubstitute(),
                createdReading.getDateOfReading()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadingDto> getReadingById(@PathVariable Long id) {

        Reading reading = readingService.getReadingById(id);

        ReadingDto dto = new ReadingDto(
                reading.getId(),
                reading.getKindOfMeter(),
                reading.getComment(),
                reading.getMeterId(),
                reading.getMeterCount(),
                reading.getSubstitute(),
                reading.getDateOfReading()
        );

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ReadingDto>> getAllReadings() {

        List<ReadingDto> readingDtos = readingService.getAllReadings()
                .stream()
                .map(reading -> new ReadingDto(
                        reading.getId(),
                        reading.getKindOfMeter(),
                        reading.getComment(),
                        reading.getMeterId(),
                        reading.getMeterCount(),
                        reading.getSubstitute(),
                        reading.getDateOfReading()
                ))
                .toList();

        return ResponseEntity.ok(readingDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadingDto> updateReading(
            @PathVariable Long id,
            @RequestBody ReadingDto readingDto) {

        if (id == null || readingDto == null) {
            return ResponseEntity.badRequest().build();
        }

        Reading reading = new Reading();
        reading.setId(readingDto.id());
        reading.setKindOfMeter(readingDto.kindOfMeter());
        reading.setComment(readingDto.comment());
        reading.setMeterId(readingDto.meterId());
        reading.setMeterCount(readingDto.meterCount());
        reading.setSubstitute(readingDto.substitute());
        reading.setDateOfReading(readingDto.dateOfReading());

        Reading updatedReading = readingService.updateReading(id, reading);

        ReadingDto responseDto = new ReadingDto(
                updatedReading.getId(),
                updatedReading.getKindOfMeter(),
                updatedReading.getComment(),
                updatedReading.getMeterId(),
                updatedReading.getMeterCount(),
                updatedReading.getSubstitute(),
                updatedReading.getDateOfReading()
        );

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReadingById(@PathVariable Long id) {

        if (!readingService.deleleteReadingById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
