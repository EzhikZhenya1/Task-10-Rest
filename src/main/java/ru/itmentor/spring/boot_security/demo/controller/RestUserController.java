package ru.itmentor.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserServiceInterface;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class RestUserController {
    private final UserServiceInterface userServiceInterface;

    @GetMapping
    public ResponseEntity<User> getUser(@AuthenticationPrincipal User userPrincipal) {
        User user = userServiceInterface.findUserById(userPrincipal.getId()).orElseThrow();
        return ResponseEntity.ok(user);
    }
}
