package project.demo.domain.service.User;

import java.util.Optional;

import project.demo.domain.entities.User;
import project.demo.infrastructure.repository.user.UserRepository;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String Login(String email, String password) {
        // Implementation of login logic
        return "User logged in with email: " + email;
    }

    @Override
    public boolean Register(User user, String password) {

        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());

        if(existingUser!= null || existingUser.isPresent()){
            return false;
        }

        byte[] passwordHash = new byte[64];
        byte[] passwordSalt = new byte[64];

        CreatePassword(passwordHash, passwordSalt, password);

        user.setPasswordHash(passwordHash);
        user.setPasswordSalt(passwordSalt);     

        userRepository.save(user);
        return true;
    }

    private void CreatePassword(byte[] hashPassword, byte[] saltPassword, String password) {
        // Implementation of password creation logic
    }

    private String GenerateToken(String email, String name) {
        // Implementation of token generation logic
        return "token_for_" + email;
    }

}
