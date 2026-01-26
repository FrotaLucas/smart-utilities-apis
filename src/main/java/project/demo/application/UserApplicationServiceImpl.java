package project.demo.application;

import project.demo.application.Interfaces.UserApplicationService;
import project.demo.domain.service.User.UserService;

public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userApplicationService;

    public UserApplicationServiceImpl(UserService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Override
    public String Login(String email, String password) {
        return userApplicationService.Login(email, password);
    }

    @Override
    public boolean Register(project.demo.domain.entities.User user, String password) {
        return userApplicationService.Register(user, password);
    }

    @Override
    public boolean ChangePassword(project.demo.domain.entities.User user, String newPassword) {
        return userApplicationService.ChangePassword(user, newPassword);
    }
}