package edu.viu.securecoding.DevSecOps.service;

import edu.viu.securecoding.DevSecOps.Utils.PasswordUtils;
import edu.viu.securecoding.DevSecOps.dto.UserDto;
import edu.viu.securecoding.DevSecOps.model.User;
import edu.viu.securecoding.DevSecOps.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Logger logger;
    private final PasswordUtils passwordUtils;

    @Autowired
    public UserServiceImpl(final Logger logger,
                           final UserRepository userRepository,
                           final PasswordUtils passwordUtils) {
        this.logger = logger;
        this.userRepository = userRepository;
        this.passwordUtils = passwordUtils;
    }

    @Override
    public Optional<UserDto> getUserById(Integer id) {

        final Optional<User> user = userRepository.findUserById(id);
        return user.map(UserDto::new);

    }

    @Override
    public Optional<UserDto> getUserByName(String name) {

        final Optional<User> user = userRepository.findByUsername(name);
        return user.map(UserDto::new);

    }

    @Override
    public List<UserDto> getAllUsers() {

        final List<User> users = userRepository.findAll();
        final List<UserDto> dtos = new ArrayList<>(users.size());

        for (User user : users) {
            dtos.add(new UserDto(user));
        }

        return dtos;
    }


    @Override
    public UserDto createUser(UserDto user) {

        final User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());

        try {
            newUser.setPassword(passwordUtils.encryptPassword(user.getPassword()));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("Error encrypting password: ", passwordUtils.removeCrlf(user.getPassword()));
        }

        final User finalUser = userRepository.save(newUser);

        logger.info("User created: " + passwordUtils.removeCrlf(finalUser.toString()));

        return new UserDto(finalUser);

    }


}
