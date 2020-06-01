package edu.viu.securecoding.DevSecOps.controller;


import edu.viu.securecoding.DevSecOps.Utils.PasswordUtils;
import edu.viu.securecoding.DevSecOps.dto.UserDto;
import edu.viu.securecoding.DevSecOps.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final Logger logger;
    private final PasswordUtils passwordUtils;

    @Autowired
    public UserControllerImpl(final UserService userService,
                              final Logger logger,
                              final PasswordUtils passwordUtils) {
        this.userService = userService;
        this.logger = logger;
        this.passwordUtils = passwordUtils;
    }

    @Override
    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(final @RequestBody @NotNull UserDto userDto) {

        logger.info("RequestBody: {}", passwordUtils.removeCrlf(userDto.toString()));

        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);

    }

    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity getUserById(final @PathVariable("id") @NotNull String id) {

        if (StringUtils.isNumeric(id)) {
            Optional<UserDto> userDto = userService.getUserById(Integer.valueOf(id));
            if (userDto.isPresent()) {

                /*
                HOW CAN WE AVOID XSS VULNERABILITY HERE?
                 */


                return ResponseEntity.ok("<html><h1>Hello: " + HtmlUtils.htmlEscape(userDto.get().getUsername()) + " </h1></htm>");
            } else {
                return ResponseEntity.ok("<html><h1>User not found</h1></htm>");
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
