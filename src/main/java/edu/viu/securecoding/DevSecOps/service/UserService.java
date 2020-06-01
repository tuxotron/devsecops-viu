package edu.viu.securecoding.DevSecOps.service;

import edu.viu.securecoding.DevSecOps.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> getUserById(Integer id);

    Optional<UserDto> getUserByName(String name);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto user);
}
