package edu.viu.securecoding.DevSecOps.controller;

import edu.viu.securecoding.DevSecOps.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

public interface UserController {
    @PostMapping("/user")
    ResponseEntity<UserDto> createUser(@RequestBody @NotNull UserDto userDto);

    @GetMapping("/user/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable("id") @NotNull String id);

}
