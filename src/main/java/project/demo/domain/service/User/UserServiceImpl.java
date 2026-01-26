package project.demo.domain.service.User;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Arrays;
import java.util.Optional;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import project.demo.JwtProperties;
import project.demo.domain.dto.PasswordDataDto;
import project.demo.domain.entities.User;
import project.demo.infrastructure.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtProperties jwtProperties;

    public UserServiceImpl(UserRepository userRepository, JwtProperties jwtProperties) {
        this.userRepository = userRepository;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public String login(String email, String password) {

        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(email);

        // toDo
        // Client dont know if user exists or password is wrong
        if (existingUser.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = existingUser.get();

        boolean isPasswordValid = verifyPassword(
                password,
                user.getPasswordHash(),
                user.getPasswordSalt());

        if (!isPasswordValid) {
            throw new RuntimeException("Invalid credentials");
        }

        return generateToken(user);
    }

    @Override
    public boolean register(User user, String password) {

        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());

        if (existingUser.isPresent()) {
            // throw new RuntimeException("user already exists");
            return false;
        }

        PasswordDataDto passwordData = createPassword(password);

        user.setPasswordHash(passwordData.hashPassword());
        user.setPasswordSalt(passwordData.saltPassword());

        userRepository.save(user);
        return true;
    }

    @Override
    public boolean changePassword(User user, String newPassword) {
        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());

        if (existingUser.isEmpty()) {
            return false;
        }

        PasswordDataDto passwordData = createPassword(newPassword);
        user.setPasswordHash(passwordData.hashPassword());
        user.setPasswordSalt(passwordData.saltPassword());
        userRepository.save(user);
        return true;
    }

    // refactor try catch
    private PasswordDataDto createPassword(String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
            SecretKey secretKey = keyGenerator.generateKey();

            // to initialize mac with secret key
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKey);

            byte[] saltPassword = secretKey.getEncoded();
            byte[] hashPassword = mac.doFinal(password.getBytes(StandardCharsets.UTF_8));

            return new PasswordDataDto(hashPassword, saltPassword);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // refactor try catch
    private boolean verifyPassword(String password, byte[] storedHash, byte[] storedSalt) {
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

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getKey() {
        return new SecretKeySpec(
                jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS512.getJcaName());
    }

}
