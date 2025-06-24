package ru.itmentor.spring.boot_security.demo.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private Set<String> roles;
}
