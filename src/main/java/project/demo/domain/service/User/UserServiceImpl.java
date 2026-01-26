package project.demo.domain.service.User;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Optional;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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

        Optional<User> existinUser = userRepository.findByEmailIgnoreCase(email);

        //toDo
        //Client dont know if user exists or password is wrong
        if (existinUser.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = existinUser.get();

        boolean isPasswordValid = VerifyPassword(
                password,
                user.getPasswordHash(),
                user.getPasswordSalt());

        if (!isPasswordValid) {
            throw new RuntimeException("Invalid credentials");
        }

        return GenerateToken(user.getEmail(), user.getRole());
    }

    @Override
    public boolean Register(User user, String password) {

        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());

        if (existingUser.isPresent()) {
            // throw new RuntimeException("user already exists");
            return false;
        }

        PasswordData passwordData = CreatePassword(password);

        user.setPasswordHash(passwordData.hashPassword());
        user.setPasswordSalt(passwordData.saltPassword());

        userRepository.save(user);
        return true;
    }

    // refactor try catch
    private PasswordData CreatePassword(String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
            SecretKey secretKey = keyGenerator.generateKey();

            // to initialize mac with secret key
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKey);

            byte[] saltPassword = secretKey.getEncoded();
            byte[] hashPassword = mac.doFinal(password.getBytes(StandardCharsets.UTF_8));

            return new PasswordData(hashPassword, saltPassword);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // refactor try catch
    private boolean VerifyPassword(String password, byte[] storedHash, byte[] storedSalt) {
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretkey = new SecretKeySpec(storedSalt, "HmacSHA512");

            mac.init(secretkey);

            byte[] computedHash = mac.doFinal(password.getBytes(StandardCharsets.UTF_8));
            return Arrays.equals(computedHash, storedHash);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String GenerateToken(String email, String name) {
        // Implementation of token generation logic
        return "token_for_" + email;
    }

}
