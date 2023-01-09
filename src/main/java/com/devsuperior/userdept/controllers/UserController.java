package com.devsuperior.userdept.controllers;

import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    @PostMapping
    public User insert(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "Deletado com sucesso!";
    }
}
