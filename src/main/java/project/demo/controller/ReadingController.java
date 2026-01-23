package project.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.demo.application.Interfaces.ReadingApplicationService;
import project.demo.model.Reading;
import project.demo.service.Reading.ReadingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/readings")
public class ReadingController {
    
    private final ReadingApplicationService readingService;

    public ReadingController(ReadingApplicationService readingService)
    {
        this.readingService = readingService;
    }   


    @PostMapping
    public ResponseEntity<Reading> createReading(@RequestBody Reading reading)
    {

        Reading createdReading = readingService.createReadingWithNewCustomer(reading);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdReading);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reading> getReadingById(@RequestParam Long id)
    {
        Reading dbReading = readingService.getReadingById(id);

        return ResponseEntity.ok(dbReading);
    }
    
    @GetMapping
    public ResponseEntity<List<Reading>> getAllReadings()
    {
        List<Reading> dbReadings = readingService.getAllReadings();

        //todo
        //handle exceptions and validation

        return ResponseEntity.ok(dbReadings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReadingById(@RequestParam Long id)
    {
        //todo
        //handle exceptions and validation id null check ??
        readingService.deleleteReadingById(id);

        return ResponseEntity.noContent().build();
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Reading> updateReading(@PathVariable Long id, @RequestBody Reading reading)
    {
        //todo
        //handle exceptions and validation

        Reading updatedReading = readingService.updateReading(id, reading);

        return ResponseEntity.ok(updatedReading);
    }

}
