package com.devsuperior.userdept.services;

import com.devsuperior.userdept.entities.Department;
import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.UserRepository;
import com.devsuperior.userdept.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com id: " + id + " não encontrado!"));
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, String name, String email, Department department) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com id: " + id + " não encontrado!"));

        user.setName(name);
        user.setEmail(email);
        user.setDepartment(department);

        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com id: " + id + " não encontrado!")).getId());
    }
}
