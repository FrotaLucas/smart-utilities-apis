package project.demo.controller;

import project.demo.service.Reading.ReadingService;

public class ReadingController {
    
    private final ReadingService readingService;

    public ReadingController(ReadingService readingService)
    {
        this.readingService = readingService;
    }   


}
