package project.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import project.demo.application.dto.ReadingDto;
import project.demo.application.interfaces.ReadingApplicationService;
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

        ReadingDto responseDto = readingService.createReading(readingDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadingDto> getReadingById(@PathVariable Long id) {

        ReadingDto responseDto = readingService.getReadingById(id);


        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ReadingDto>> getAllReadings() {

        List<ReadingDto> readingDtos = readingService.getAllReadings();
               
        return ResponseEntity.ok(readingDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadingDto> updateReading(
            @PathVariable Long id,
            @RequestBody ReadingDto readingDto) {

        if (id == null || readingDto == null) {
            return ResponseEntity.badRequest().build();
        }

    
        ReadingDto responseDto = readingService.updateReading(id, readingDto);

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
