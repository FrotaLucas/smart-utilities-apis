package project.demo.dto;

public record PasswordData(
        byte[] hashPassword,
        byte[] saltPassword
) {}