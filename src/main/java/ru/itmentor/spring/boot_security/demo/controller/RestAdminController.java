package ru.itmentor.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserServiceInterface;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class RestAdminController {
    private final UserServiceInterface userServiceInterface;

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        List<User> users = userServiceInterface.findUsers();
        return ResponseEntity.ok(users);
    }

   @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userServiceInterface.saveUser(user);
        return ResponseEntity.ok(user);
   }

   @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        userServiceInterface.saveUser(user);
        return ResponseEntity.ok(user);
   }

   @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        userServiceInterface.deleteUser(id);
        return ResponseEntity.noContent().build();
   }

   @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return userServiceInterface.findUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
   }
}
