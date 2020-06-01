package edu.viu.securecoding.DevSecOps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

public interface ImageController {
    @GetMapping("/image")
    ResponseEntity<byte[]> getImage(@RequestParam String imageName) throws IOException;
}
