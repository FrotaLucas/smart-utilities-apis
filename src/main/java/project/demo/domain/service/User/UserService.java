package project.demo.domain.service.User;


public interface UserService {
 
    String Login(String email, String password);
    
    String Register(String email, String password, String name);
}
