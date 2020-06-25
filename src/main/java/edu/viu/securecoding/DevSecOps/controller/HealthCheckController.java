package edu.viu.securecoding.DevSecOps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface HealthCheckController {

    @GetMapping("/healthcheck")
    ResponseEntity<String> getHealthCheck();
}
