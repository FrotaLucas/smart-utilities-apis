package project.demo.domain.service.User;

public class UserServiceImpl implements UserService {

    @Override
    public String Login(String email, String password) {
        // Implementation of login logic
        return "User logged in with email: " + email;
    }

    @Override
    public String Register(String email, String password, String name) {
        // Implementation of registration logic
        return "User registered with email: " + email + " and name: " + name;
    }
    

    private String CreatePassword( byte[] hash, byte[] salt, String password){
        // Implementation of password creation logic
        return "hashed_" + password;
    }

    private String GenerateToken(String email, String name){
        // Implementation of token generation logic
        return "token_for_" + email;
    }
}
