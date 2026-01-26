package project.demo.domain.dto;

public record PasswordDataDto(
        byte[] hashPassword,
        byte[] saltPassword
) {}