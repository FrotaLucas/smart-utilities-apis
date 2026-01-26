package project.demo.domain.service.User;

import project.demo.domain.entities.User;
public interface UserService {
 
    String Login(String email, String password);

    boolean Register(User user, String password);

    boolean ChangePassword(User user, String newPassword);
}
