package project.demo.application.dto;

import java.time.LocalDate;

public record ReadingDto(
    Long id,
    String kindOfMeter,
    String comment,
    String meterId,
    Double meterCount,
    boolean substitute,
    LocalDate dateOfReading
) {}