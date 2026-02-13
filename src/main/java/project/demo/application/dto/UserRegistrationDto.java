package project.demo.application.dto;

public record UserRegistrationDto(
    String firstName,
    String lastName,
    String email,
    String password
) {}