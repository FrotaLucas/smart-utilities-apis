package project.demo.application.Interfaces; //only lowercase names to use, by convention

import project.demo.domain.entities.User;

public interface UserApplicationService {

    String Login(String email, String password);

    boolean Register(User user, String password);

    boolean ChangePassword(User user, String newPassword);
}
