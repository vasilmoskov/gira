package bg.softuni.gira.service;

import bg.softuni.gira.models.dto.LoginDto;
import bg.softuni.gira.models.dto.RegisterDto;
import bg.softuni.gira.models.entities.UserEntity;

public interface UserService {
    void register(RegisterDto registerDto);

    boolean loginSuccessful(LoginDto loginDto);

    void logout();
}
