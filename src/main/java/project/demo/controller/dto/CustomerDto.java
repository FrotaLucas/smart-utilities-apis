package project.demo.controller.dto;

import java.time.LocalDate;
// Define all DTO types together (maybe under 'domain.dto' package). No need for 'controller.dto' package.
public record CustomerDto(
    Long id,
    String firstName,
    String lastName,
    String gender,
    LocalDate birthDate
) {}