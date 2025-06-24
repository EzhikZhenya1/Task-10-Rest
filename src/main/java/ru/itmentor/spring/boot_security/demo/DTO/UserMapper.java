package ru.itmentor.spring.boot_security.demo.DTO;

import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstname());
        userDTO.setLastName(user.getLastname());
        userDTO.setAge(user.getAge());
        userDTO.setRoles(user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        return userDTO;
    }
}
