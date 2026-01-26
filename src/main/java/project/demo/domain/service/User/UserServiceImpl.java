package project.demo.domain.service.User;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import project.demo.domain.entities.User;
import project.demo.dto.PasswordData;
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

        if (existingUser != null || existingUser.isPresent()) {
            return false;
        }

        PasswordData passwordData = CreatePassword(password);

        user.setPasswordHash(passwordData.hashPassword());
        user.setPasswordSalt(passwordData.saltPassword());

        userRepository.save(user);
        return true;
    }

    private PasswordData CreatePassword(String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
            SecretKey secretKey = keyGenerator.generateKey();

            //to initialize mac with secret key
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKey);

            byte[] hashPassword = mac.doFinal(password.getBytes(StandardCharsets.UTF_8));
            byte[] saltPassword = secretKey.getEncoded();

            return new PasswordData(hashPassword, saltPassword);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String GenerateToken(String email, String name) {
        // Implementation of token generation logic
        return "token_for_" + email;
    }

}
