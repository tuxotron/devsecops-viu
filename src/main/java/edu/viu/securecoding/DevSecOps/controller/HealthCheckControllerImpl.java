package edu.viu.securecoding.DevSecOps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckControllerImpl implements HealthCheckController {
    @Override
    public ResponseEntity<String> getHealthCheck() {
        return ResponseEntity.ok("ok");
    }
}
