package project.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.demo.application.Interfaces.UserApplicationService;
import project.demo.controller.dto.LoginRequestDto;
import project.demo.controller.dto.TokenResponseDto;
import project.demo.controller.dto.UserChangePasswordDto;
import project.demo.controller.dto.UserRegistrationDto;
import project.demo.domain.entities.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;



@RestController
@RequestMapping("/api/v1/users") // Versione sua API
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegistrationDto userDto) {
        
        User newUser = new User();
        newUser.setFirstName(userDto.firstName());
        newUser.setLastName(userDto.lastName());
        newUser.setEmail(userDto.email());

        String password = userDto.password();


        boolean isCreated = userApplicationService.Register(newUser, password);
        
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Login with post is more secure than get
    @PostMapping("/login") 
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        String token = userApplicationService.Login(loginRequest.email(), loginRequest.password());
        
        if (token != null) {
            return ResponseEntity.ok(new TokenResponseDto(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/password") 
    public ResponseEntity<Void> changePassword(@RequestBody UserChangePasswordDto userDto) {

        User user = new User();
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setEmail(userDto.email());

        String newPassword = userDto.newPassword();

        boolean isUpdated = userApplicationService.ChangePassword(user, newPassword);
        
        return isUpdated ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}