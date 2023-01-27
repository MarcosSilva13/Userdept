package com.devsuperior.userdept.controllers;

import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> insert(@RequestBody User user) {
        User userSaved = userService.insert(user);

        return ResponseEntity.status(HttpStatus.OK).body(userSaved);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        User userUpdated = userService.update(user.getId(),
                user.getName(), user.getEmail(), user.getDepartment());

        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
    }
}
