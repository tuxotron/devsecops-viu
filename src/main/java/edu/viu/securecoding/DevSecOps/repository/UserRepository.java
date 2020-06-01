package edu.viu.securecoding.DevSecOps.repository;

import edu.viu.securecoding.DevSecOps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(final String username);

    Optional<User> findUserById(final Integer id);

}
