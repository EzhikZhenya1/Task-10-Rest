package ru.itmentor.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.DTO.UserDTO;
import ru.itmentor.spring.boot_security.demo.DTO.UserMapper;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserServiceInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class RestAdminController {
    private final UserServiceInterface userServiceInterface;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> users() {
        List<UserDTO> users = userServiceInterface.findUsers()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

   @PostMapping("/users")
    public ResponseEntity<UserDTO> saveUser(@RequestBody User user){
        userServiceInterface.saveUser(user);
        return ResponseEntity.ok(UserMapper.toDTO(user));
   }

   @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        userServiceInterface.saveUser(user);
        return ResponseEntity.ok(UserMapper.toDTO(user));
   }

   @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
        userServiceInterface.deleteUser(id);
        return ResponseEntity.noContent().build();
   }

   @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id){
        return userServiceInterface.findUserById(id)
                .stream()
                .map(UserMapper::toDTO)
                .map(ResponseEntity::ok)
                .findFirst().orElse(ResponseEntity.notFound().build());
   }
}
